/*
 * 获取一年中的节日
 */
var FestivalDay = {
  getDay: function(id){
    var _ = [];
    _['0101'] = '元旦';
    _['0106'] = '小寒';
    _['0115'] = '腊八';
    _['0121'] = '大寒';
    _['0204'] = '立春';
    _['0206'] = '除夕';
    _['0207'] = '春节';
    _['0214'] = '情人节';
    _['0219'] = '雨水';
    _['0221'] = '元宵节';
    _['0303'] = '全国爱耳日';
    _['0305'] = '惊蛰';
    _['0308'] = '国际妇女节';
    _['0312'] = '植树节';
    _['0315'] = '消费者权益日';
    _['0320'] = '春分';
    _['0321'] = '世界森林日';
    _['0322'] = '世界水日';
    _['0323'] = '世界气象日';
    _['0401'] = '国际愚人节';
    _['0405'] = '清明节';
    _['0407'] = '世界卫生日';
    _['0420'] = '谷雨';
    _['0422'] = '世界地球日';
    _['0501'] = '国际劳动节';
    _['0504'] = '青年节';
    _['0505'] = '立夏';
    _['0508'] = '世界红十字日';
    _['0510'] = '母亲节';
    _['0512'] = '国际护士节';
    _['0517'] = '世界电信日';
    _['0521'] = '小满';
    _['0531'] = '世界无烟日';
    _['0601'] = '国际儿童节';
    _['0605'] = '芒种';
    _['0606'] = '全国爱眼日';
    _['0608'] = '端午节';
    _['0621'] = '父亲节 夏至';
    _['0623'] = '国际奥林匹克日';
    _['0626'] = '国际禁毒日';
    _['0701'] = '中国共产党诞辰';
    _['0707'] = '小暑 抗战纪念日';
    _['0722'] = '大暑';
    _['0801'] = '建军节';
    _['0807'] = '立秋 七夕';
    _['0812'] = '国际青年日';
    _['0823'] = '处暑';
    _['0907'] = '白露';
    _['0910'] = '中国教师节';
    _['0914'] = '中秋节';
    _['0918'] = '九一八纪念日';
    _['0920'] = '全国爱牙日';
    _['0922'] = '秋分';
    _['1001'] = '国庆节';
    _['1007'] = '重阳节';
    _['1008'] = '寒露';
    _['1015'] = '国际盲人节';
    _['1023'] = '霜降';
    _['1107'] = '立冬';
    _['1108'] = '中国记者节';
    _['1109'] = '中国消防宣传日';
    _['1110'] = '世界青年节';
    _['1117'] = '国际大学生节';
    _['1122'] = '小雪/感恩节';
    _['1201'] = '世界艾滋病日';
    _['1203'] = '世界残疾人日';
    _['1207'] = '大雪';
    _['1209'] = '一二九纪念日';
    _['1212'] = '西安事变纪念日';
    _['1221'] = '冬至';
    _['1225'] = '圣诞节';
    return _[id];
  },
  getWeek: function(id){
    var _ = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    return _[id];
  },
  getString: function(){
    var now = new Date();
    var y = now.getFullYear();
    var m = now.getMonth() + 1;
    var d = now.getDate();
    var w = now.getDay();
    m < 10 ? m = 0 + '' + m : 0;
    d < 10 ? d = 0 + '' + d : 0;
    var index = m + '' + d;
    var festival = this.getDay(index);
    if (!festival || y != 2008) {
      festival = '';
    }
    var r = y + '年' + (m - 0) + '月' + (d - 0) + '日　' + this.getWeek(w) + '　' + festival;
    return r;
  },
  show: function(){
    document.write(this.getString());
  }}
