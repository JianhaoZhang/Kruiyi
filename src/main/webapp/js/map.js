//创建和初始化地图函数：
	    function initMap(){
	      createMap();//创建地图
	      setMapEvent();//设置地图事件
	      addMapControl();//向地图添加控件
	      addMapOverlay();//向地图添加覆盖物
	    }
	    function createMap(){ 
	        map1 = new BMap.Map("map1"); 
	        map1.centerAndZoom(new BMap.Point(113.957119,22.545049),17);
	        map2 = new BMap.Map("map2"); 
	        map2.centerAndZoom(new BMap.Point(114.220403,22.326692),17);
	        map3 = new BMap.Map("map3"); 
	        map3.centerAndZoom(new BMap.Point(121.493574,31.214522),17);
	        map4 = new BMap.Map("map4"); 
	        map4.centerAndZoom(new BMap.Point(120.674735,31.321512),17);
	        map5 = new BMap.Map("map5"); 
	        map5.centerAndZoom(new BMap.Point(112.918846,28.208413),17);
	      }
	      function setMapEvent(){
	        map1.enableScrollWheelZoom();
	        map1.enableDragging();
	        map1.enableDoubleClickZoom();
	        map2.enableScrollWheelZoom();
	        map2.enableDragging();
	        map2.enableDoubleClickZoom();
	        map3.enableScrollWheelZoom();
	        map3.enableDragging();
	        map3.enableDoubleClickZoom();
	        map4.enableScrollWheelZoom();
	        map4.enableDragging();
	        map4.enableDoubleClickZoom();
	        map5.enableScrollWheelZoom();
	        map5.enableDragging();
	        map5.enableDoubleClickZoom();
	      }
	      function addClickHandler(target,window){
	        target.addEventListener("click",function(){
	          target.openInfoWindow(window);
	        });
	      }
	      function addMapOverlay(){
	        var markers = [
	          {content:"",title:"",imageOffset: {width:-46,height:-21},position:{lat:22.544886,lng:113.957087}},
	          {content:"",title:"",imageOffset: {width:-46,height:-21},position:{lat:22.327035,lng:114.219725}},
	          {content:"",title:"",imageOffset: {width:-46,height:-21},position:{lat:31.214642,lng:121.49335}},
	          {content:"",title:"",imageOffset: {width:-46,height:-21},position:{lat:31.321551,lng:120.674775}},
	          {content:"",title:"",imageOffset: {width:-46,height:-21},position:{lat:28.208361,lng:112.918922}}
	        ];
	        var maps = [map1,map2,map3,map4,map5];
	        for(var index = 0; index < markers.length; index++ ){
	          var point = new BMap.Point(markers[index].position.lng,markers[index].position.lat);
	          var marker = new BMap.Marker(point,{icon:new BMap.Icon("http://api.map.baidu.com/lbsapi/createmap/images/icon.png",new BMap.Size(20,25),{
	            imageOffset: new BMap.Size(markers[index].imageOffset.width,markers[index].imageOffset.height)
	          })});
	          maps[index].addOverlay(marker);
	        };
	      }
	      //向地图添加控件
	      function addMapControl(){
	        var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
	        var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	        map1.addControl(scaleControl);
	        map1.addControl(navControl);
	        map2.addControl(scaleControl);
	        map2.addControl(navControl);
	        map3.addControl(scaleControl);
	        map3.addControl(navControl);
	        map4.addControl(scaleControl);
	        map4.addControl(navControl);
	        map5.addControl(scaleControl);
	        map5.addControl(navControl);
	      }
	    var map1;
	    var map2;
	    var map3;
	    var map4;
	    var map5;