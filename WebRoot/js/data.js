// JavaScript Document
  var start = {
  elem: '#start',
  format: 'YYYY-MM-DD',
  min: '2017-01-01', //设定最小日期为当前日期
  max: laydate.now(), //最大日期
  istime: true,
  istoday: false,
  choose: function(datas){
     end.min = datas; //开始日选好后，重置结束日的最小日期
     end.start = datas //将结束日的初始值设定为开始日
  }
};
var end = {
  elem: '#end',
  format: 'YYYY-MM-DD',
  min: laydate.now(),
  max:laydate.now(),
  istime: true,
  istoday: false,
  choose: function(datas){
    start.max = datas; //结束日选好后，重置开始日的最大日期
  }
};
laydate(start);
laydate(end);