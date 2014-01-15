//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
//★															日期输入                                  ★
//★使用案例：                                                              ★
//★<input id='startDate' name='startDate' onclick='setdate(this)' />       ★
//★                                                                        ★
//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
<!--
document.writeln("<div id=\"DateLayer\" style=\"position: absolute; width: 142; height: 166; z-index: 9998; display: none\">");
document.writeln("			<span id=\"tmpSelectYearLayer\" style=\"z-index: 9999; position: absolute; top: 2; left: 18; display: none\"></span>");
document.writeln("			<span id=\"tmpSelectMonthLayer\" style=\"z-index: 9999; position: absolute; top: 2; left: 75; display: none\"></span>");
document.writeln("			<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"142\" height=\"160\" bgcolor=\"#000000\" onselectstart=\"return false\">");
document.writeln("				<tr>");
document.writeln("					<td width=\"142\" height=\"23\" bgcolor=\"#FFFFFF\">");
document.writeln("						<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"140\" height=\"23\">");
document.writeln("							<tr align=\"center\">");
document.writeln("								<td width=\"20\" align=\"center\" bgcolor=\"#808080\" style=\"font-size:12px; cursor:hand; color:#FFD700\" onclick=\"PrevM()\" title=\"前一月\"><b>&lt;</b>");
document.writeln("								</td>");
document.writeln("								<td width=\"100\" align=\"center\" style=\"font-size: 12px; cursor: default\">");
document.writeln("									<span id=\"YearHead\" onmouseover=\"style.backgroundColor='#FFd700'\" onmouseout=\"style.backgroundColor='white'\" title=\"点击这里选择年份\" onclick=\"tmpSelectYearInnerHTML(this.innerText)\" style=\"cursor: hand;\"></span>&nbsp;年&nbsp;");
document.writeln("									<span id=\"MonthHead\" onmouseover=\"style.backgroundColor='#FFd700'\" onmouseout=\"style.backgroundColor='white'\" title=\"点击这里选择月份\" onclick=\"tmpSelectMonthInnerHTML(this.innerText)\" style=\"cursor: hand;\"></span>&nbsp;月");
document.writeln("								</td>");
document.writeln("								<td width=\"20\" bgcolor=\"#808080\" align=\"center\" style=\"font-size: 12px; cursor: hand; color: #FFD700\" onclick=\"NextM()\" title=\"后一月\"><b>&gt;</b>");
document.writeln("								</td>");
document.writeln("							</tr>");
document.writeln("						</table>");
document.writeln("					</td>");
document.writeln("				</tr>");
document.writeln("				<tr>");
document.writeln("					<td width=\"142\" height=\"18\" bgcolor=\"#808080\">");
document.writeln("						<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"140\" height=\"1\" style=\"cursor: default\">");
document.writeln("							<tr align=\"center\">");
document.writeln("								<td style=\"font-size: 12px; color:red\">日</td>");
document.writeln("								<td style=\"font-size: 12px; color: #FFFFFF\">一</td>");
document.writeln("								<td style=\"font-size: 12px; color: #FFFFFF\">二</td>");
document.writeln("								<td style=\"font-size: 12px; color: #FFFFFF\">三</td>");
document.writeln("								<td style=\"font-size: 12px; color: #FFFFFF\">四</td>");
document.writeln("								<td style=\"font-size: 12px; color: #FFFFFF\">五</td>");
document.writeln("								<td style=\"font-size: 12px; color:red\">六</td>");
document.writeln("							</tr>");
document.writeln("						</table>");
document.writeln("					</td>");
document.writeln("				</tr>");
document.writeln("				<tr>");
document.writeln("					<td width=\"142\" height=\"120\">");
document.writeln("						<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"140\" height=\"120\" bgcolor=\"#F0F0F0\">");
var n=0; 
for(j=0; j<5; j++){
	document.writeln("<tr align='center'>");
	for(i=0; i<7; i++){
		if(0==i)
			document.writeln("<td width='20' height='20' id='Day"+n
											+"' style='font-size: 12px; color:red;' onclick='DayClick(this.innerText)'></td>");
		else if(6==i)
			document.writeln("<td width='20' height='20' id='Day"+n
									+"' style='font-size: 12px; color:red;' onclick='DayClick(this.innerText)'></td>");
		else
			document.writeln("<td width='20' height='20' id='Day"+n
										+"' style='font-size: 12px' onclick='DayClick(this.innerText)'></td>");
			n++;
	}
	document.writeln("</tr>");
}
document.writeln("							<tr align=\"center\">");
document.writeln("								<td width=\"20\" height=\"20\" style=\"font-size: 12px; color:red;\" id=\"Day35\" onclick=\"DayClick(this.innerText)\"></td>");
document.writeln("								<td width=\"20\" height=\"20\" style=\"font-size: 12px\" id=\"Day36\" onclick=\"DayClick(this.innerText)\"></td>");
document.writeln("								<td colspan=\"5\" align=\"right\">");
//document.writeln("									<span onclick=\"closeLayer()\" style=\"font-size:12px; cursor:hand; color:blue;font-weight:bold\" title=\"返回（不选择日期）\"><u>关闭</u></span>&nbsp;");
document.writeln("									<input type=\"button\" value=\"关闭窗口\" onclick=\"closeLayer()\" title=\"返回（不选择日期）\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; FONT-SIZE: 12px; height: 20px; color: blue; font-weight: bold\" />");
document.writeln("								</td>");
document.writeln("							</tr>");
document.writeln("						</table>");
document.writeln("					</td>");
document.writeln("				</tr>");
document.writeln("				<tr>");
document.writeln("					<td>");
document.writeln("						<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#FFFFFF\">");
document.writeln("							<tr>");
document.writeln("								<td align=\"left\">");
document.writeln("									<input type=\"button\" value=\"&lt;&lt;\" title=\"前一年\" onclick=\"PrevY()\" onfocus=\"this.blur()\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; FONT-SIZE: 12px; height: 20px; color: #FFD700; font-weight: bold\" />");
document.writeln("									<input title=\"前一月\" type=\"button\" value=\"&lt;\" onclick=\"PrevM()\" onfocus=\"this.blur()\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; font-size: 12px; height: 20px; color: #FFD700; font-weight: bold\" />");
document.writeln("								</td>");
document.writeln("								<td align=\"center\">");
document.writeln("									<input type=\"button\" value=\"重置\" onclick=\"Today()\" onfocus=\"this.blur()\" title=\"显示当前时间\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; font-size: 12px; height: 20px; color: blue; font-weight: bold\" />");
document.writeln("								</td>");
document.writeln("								<td align=\"right\">");
document.writeln("									<input type=\"button\" value=\"&gt;\" onclick=\"NextM()\" onfocus=\"this.blur()\" title=\"后一月\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; font-size: 12px; height: 20px; color: #FFD700; font-weight: bold\" />");
document.writeln("									<input type=\"button\" value=\"&gt;&gt;\" title=\"后一年\" onclick=\"NextY()\" onfocus=\"this.blur()\" style=\"cursor: hand; BACKGROUND-COLOR: #808080; BORDER-BOTTOM: #808080 1px outset; BORDER-LEFT: #808080 1px outset; BORDER-RIGHT: #808080 1px outset; BORDER-TOP: #808080 1px outset; font-size: 12px; height: 20px; color: #FFD700; font-weight: bold\" />");
document.writeln("								</td>");
document.writeln("							</tr>");
document.writeln("						</table>");
document.writeln("					</td>");
document.writeln("				</tr>");
document.writeln("			</table>");
document.writeln("		</div>");

var outObject;
//主调函数
function setdate(tt){
  if (arguments.length!=1) { alert("对不起！传入本控件的参数错误！"); return; }
  var dads  = document.all.DateLayer.style;
	var th 		= tt;
  var ttop  = tt.offsetTop;     //TT控件的定位点高
  var thei  = tt.clientHeight;  //TT控件本身的高
  var tleft = tt.offsetLeft;    //TT控件的定位点宽
  var ttyp  = tt.type;          //TT控件的类型
	//alert("tt.offsetTop="+ttop+", tt.clientHeight="+thei+", tt.offsetLeft="+tleft+", tt.type="+ttyp);
  while (tt = tt.offsetParent){
		ttop += tt.offsetTop;
		tleft += tt.offsetLeft;
	}
	
  dads.top  = (ttyp=="image") ? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = th;
  dads.display = '';
  event.returnValue = false;
}
var MonHead = new Array(12); //定义阳历中每个月的最大天数
    MonHead[0] = 31;
		MonHead[1] = 28;
		MonHead[2] = 31;
		MonHead[3] = 30;
		MonHead[4] = 31;
		MonHead[5] = 30;
    MonHead[6] = 31;
		MonHead[7] = 31;
		MonHead[8] = 30;
		MonHead[9] = 31;
		MonHead[10] = 30;
		MonHead[11] = 31;

var tmpDate = new Date();
var TheYear = tmpDate.getFullYear(); //定义年的变量的初始值
var TheMonth = tmpDate.getMonth() + 1; //定义月的变量的初始值
var WDay = new Array(37); //定义写日期的数组

// 任意点击时关闭该控件
/*function document.onclick(){
  with(window.event.srcElement){
		if (tagName!="input" && getAttribute("title")==null)
			document.all.DateLayer.style.display="none";
  }
}*/
//往 head 中写入当前的年与月
function WriteHead(yy,mm){
	document.all.YearHead.innerText  = yy;
	document.all.MonthHead.innerText = mm;
}
//年份的下拉框
function tmpSelectYearInnerHTML(strYear) {
  if (strYear.match(/\D/) != null){
		alert("年份输入参数不是数字！");
		return;
	}
  var m = (strYear) ? strYear : tmpDate.getFullYear();
  if (m<1990 || m>2099) {
		alert("年份值不在 1990 到 2099 之间！");
		return;
	}
  var n = parseInt(m)-10;
  if (n<1990)
		n = 1990;
  if (n+10>2099)
		n = 2099;
  var s = "<select name=\"tmpSelectYear\" style=\"font-size: 12px\" "
     s += "onblur=\"document.all.tmpSelectYearLayer.style.display='none'\" "
     s += "onchange=\"document.all.tmpSelectYearLayer.style.display='none';"
     s += "TheYear=this.value;SetDay(TheYear,TheMonth)\">\r\n";
  var selectInnerHTML = s;
	//alert(parseInt(m)+1);
  for (var i=n; i<(parseInt(m)+1); i++){
    if (i==m)
			selectInnerHTML += "<option value=\""+i+"\" selected>"+i+"年"+"</option>\r\n";
    else
			selectInnerHTML += "<option value=\""+i+"\">"+i+"年"+"</option>\r\n";
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}
//月份的下拉框
function tmpSelectMonthInnerHTML(strMonth) {
  if(strMonth.match(/\D/)!=null){
		alert("月份输入参数不是数字！");
		return;
	}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select name=\"tmpSelectMonth\" style=\"font-size: 12px\" "
     s += "onblur=\"document.all.tmpSelectMonthLayer.style.display='none'\" "
     s += "onchange=\"document.all.tmpSelectMonthLayer.style.display='none';"
     s += "TheMonth=this.value;SetDay(TheYear,TheMonth)\">\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++){
    if (i == m)
			selectInnerHTML += "<option value=\""+i+"\" selected>"+i+"月"+"</option>\r\n";
    else
			selectInnerHTML += "<option value=\""+i+"\">"+i+"月"+"</option>\r\n";
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectMonthLayer.style.display="";
  document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectMonth.focus();
}
//这个层的关闭
function closeLayer(){
	document.all.DateLayer.style.display="none";
}
function document.onkeydown(){
	if(window.event.keyCode==27)
		document.all.DateLayer.style.display="none";
}
//判断是否闰平年
function IsPinYear(year){
	if(0==(year%4) && ((year%100!=0) || (year%400==0)))
		return true;
	else
		return false;
}
//闰年二月为29天
function GetMonthCount(year,month){
	var c=MonHead[month-1];
	if((month==2) && IsPinYear(year))
		c++;
	return c;
}
//求某天的星期几
function GetDOW(day,month,year){
	var dt=new Date(year,month-1,day).getDay()/7;
	return dt;
}
//往前翻 Year
function PrevY(){
	if(TheYear>1990 && TheYear<=2099)	TheYear--;
	else	alert("年份超出范围（1991-2099）！");
	SetDay(TheYear,TheMonth);
}
//往后翻 Year
function NextY(){
	if(TheYear>1990 && TheYear<=2099)	TheYear++;
	else	alert("年份超出范围（1991-2099）！");
	SetDay(TheYear,TheMonth);
}
//Today Button
function Today(){
	TheYear = new Date().getFullYear();
	TheMonth = new Date().getMonth()+1;
	SetDay(TheYear,TheMonth);
}
//往前翻月份
function PrevM(){
	if(TheMonth>1)
		TheMonth--;
	else{
		TheYear--;
		TheMonth=12;
	}
	SetDay(TheYear,TheMonth);
}
//往后翻月份
function NextM(){
	if(TheMonth==12){
		TheYear++;
		TheMonth=1;
	}else	TheMonth++;
	SetDay(TheYear,TheMonth);
}
//主要的写程序
//设置日期
function SetDay(yy, mm){
  WriteHead(yy,mm);
  for(var i=0; i<37; i++)
		WDay[i]=""; //将显示框的内容全部清空
  var day1=1, firstday=new Date(yy,mm-1,1).getDay(); //某月第一天的星期几
  for(var i=firstday; day1<GetMonthCount(yy,mm)+1; i++){
		WDay[i]=day1;
		day1++;
	}
  for(var i=0; i<37; i++){
		var da = eval("document.all.Day"+i); //书写新的一个月的日期星期排列
    if (WDay[i]!=""){
			da.innerHTML="<b>"+WDay[i]+"</b>";
			var tmpDate=new Date();
			var isCurDate=(yy==tmpDate.getFullYear() && mm==(tmpDate.getMonth()+1) && WDay[i]==tmpDate.getDate());
			da.style.backgroundColor = (isCurDate) ? "#FFD700" : "#B0C4DE";
			da.style.fontStyle=(isCurDate) ? "italic" : "normal";
			da.style.cursor="hand";
		}else{
			da.innerHTML="";
			da.style.backgroundColor="";
			da.style.cursor="default";
		}
	}
}
//点击显示框选取日期，主输入函数*************
function DayClick(n){
	var yy = TheYear;
	var mm = TheMonth;
	if(mm<10)	mm="0"+mm;
	if(outObject){
		if(!n){
			outObject.value="";
			return;
		}
		if(n<10)	n="0"+n;
		outObject.value=yy+"-"+mm+"-"+n; //注：在这里你可以输出改成你想要的格式
		closeLayer(); 
	}else{
		closeLayer();
		alert("您所要输出的控件对象并不存在！");
	}
}
SetDay(TheYear,TheMonth);
// -->