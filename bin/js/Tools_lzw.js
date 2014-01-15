

/*
 * ����    �� ����AJAX�������
 * �������ڣ� 2008-09-04
 */
function createXmlHttp(){
  var xmlreq = false;
  if (window.XMLHttpRequest) {
    xmlreq = new XMLHttpRequest();
  }else if (window.ActiveXObject) {
    try {
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    }catch (e1) {
      try {
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      }catch (e2) {
      }
    }
  }
  return xmlreq;
}

/*
 * ����    �� �Զ�����<img>�е�ͼƬ�Ŀ��
 * �������ڣ� 2008-09-04
 */
function ImgAuto(i){//������ͼƬ��ǰ�趨��ʼ��Ⱥ͸߶ȣ�����ֱ�Ӿ�������Ⱥ͸߶�
  var MaxW = 530; //����ͼƬ��ʾ�������
  var o = new Image();
  o.src = i.src;
  var w = o.width;
  var t;
  if (w > MaxW) {
    t = MaxW;
  }
  else {
    t = w;
  }
  i.width = t;
}

/*
 * ����    �� У���¼����ֻ������6-25������ĸ��ͷ���ɴ����֡���_������.�����ִ�
 * �������ڣ� 2008-09-01 
 */
function isRegisterUserName(s){
 var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,24}$/;
 if (!patrn.exec(s)) return false;
 return true;
}

/*
 * ����    �� ��ʽ��ʱ��
 * ʾ��    �� DateUtil.Format("yyyy/MM/dd","Thu Nov 9 20:30:37 UTC+0800 2006 ");
 * ����    �� 2006/11/09
 * �������ڣ� 2008-09-01
 */
function DateUtil(){}
DateUtil.Format=function(fmtCode,date){
	var result,d,arr_d;
	var patrn_now_1=/^y{4}-M{2}-d{2}\sh{2}:m{2}:s{2}$/;
	var patrn_now_11=/^y{4}-M{1,2}-d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;
	var patrn_now_2=/^y{4}\/M{2}\/d{2}\sh{2}:m{2}:s{2}$/;
	var patrn_now_22=/^y{4}\/M{1,2}\/d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;
	var patrn_now_3=/^y{4}��M{2}��d{2}��\sh{2}ʱm{2}��s{2}��$/;
	var patrn_now_33=/^y{4}��M{1,2}��d{1,2}��\sh{1,2}ʱm{1,2}��s{1,2}��$/;
	var patrn_date_1=/^y{4}-M{2}-d{2}$/;
	var patrn_date_11=/^y{4}-M{1,2}-d{1,2}$/;
	var patrn_date_2=/^y{4}\/M{2}\/d{2}$/;
	var patrn_date_22=/^y{4}\/M{1,2}\/d{1,2}$/;
	var patrn_date_3=/^y{4}��M{2}��d{2}��$/;
	var patrn_date_33=/^y{4}��M{1,2}��d{1,2}��$/;
	var patrn_time_1=/^h{2}:m{2}:s{2}$/;
	var patrn_time_11=/^h{1,2}:m{1,2}:s{1,2}$/;
	var patrn_time_2=/^h{2}ʱm{2}��s{2}��$/;
	var patrn_time_22=/^h{1,2}ʱm{1,2}��s{1,2}��$/;

	if(!fmtCode){	fmtCode="yyyy-MM-dd hh:mm:ss";	}
	if(date){
		d=new Date(date);
		if(isNaN(d)){
			msgBox("ʱ������Ƿ�\n��ȷ��ʱ��ʾ��:\nThu Nov 9 20:30:37 UTC+0800 2006\n��\n2006/10/17");
			return;
		}
	}else{	d=new Date();	}

	if(patrn_now_1.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_now_11.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_now_2.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_now_22.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_now_3.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"��"+arr_d.MM+"��"+arr_d.dd+"��"+" "+arr_d.hh+"ʱ"+arr_d.mm+"��"+arr_d.ss+"��";
	}else if(patrn_now_33.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"��"+arr_d.MM+"��"+arr_d.dd+"��"+" "+arr_d.hh+"ʱ"+arr_d.mm+"��"+arr_d.ss+"��";
	}else if(patrn_date_1.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd;
	}else if(patrn_date_11.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd;
	}else if(patrn_date_2.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd;
	}else if(patrn_date_22.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd;
	}else if(patrn_date_3.test(fmtCode)){   
		arr_d=splitDate(d,true);
		result=arr_d.yyyy+"��"+arr_d.MM+"��"+arr_d.dd+"��";
	}else if(patrn_date_33.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.yyyy+"��"+arr_d.MM+"��"+arr_d.dd+"��";
	}else if(patrn_time_1.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_time_11.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;
	}else if(patrn_time_2.test(fmtCode)){
		arr_d=splitDate(d,true);
		result=arr_d.hh+"ʱ"+arr_d.mm+"��"+arr_d.ss+"��";
	}else if(patrn_time_22.test(fmtCode)){
		arr_d=splitDate(d);
		result=arr_d.hh+"ʱ"+arr_d.mm+"��"+arr_d.ss+"��";
	}else{
		msgBox("û��ƥ���ʱ���ʽ!");
		return;
	}
	return result;
};
function splitDate(d,isZero){
	var yyyy,MM,dd,hh,mm,ss;
	if(isZero){ 
		yyyy=d.getYear();
		MM=(d.getMonth()+1)<10?"0"+(d.getMonth()+1):d.getMonth()+1; 
		dd=d.getDate()<10?"0"+d.getDate():d.getDate(); 
		hh=d.getHours()<10?"0"+d.getHours():d.getHours(); 
		mm=d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes(); 
		ss=d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds();
	}else{
		yyyy=d.getYear();
		MM=d.getMonth()+1;
		dd=d.getDate(); 
		hh=d.getHours();
		mm=d.getMinutes();  
		ss=d.getSeconds(); 
	}
	return {"yyyy":yyyy,"MM":MM,"dd":dd,"hh":hh,"mm":mm,"ss":ss};  
}
function msgBox(msg){
	window.alert(msg);
}