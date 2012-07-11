/*
 *  Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


Ext.require([
    '*'
]);



var tmp1,tmp2,tmp3,tmp4,tmp5;

var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

/**
 * Variable that define the Cell Editing Plugin
 */
var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
    clicksToEdit: 2
});
/**
 * Variable that define the Cell Editing Plugin
 */
var cellEditing1 = Ext.create('Ext.grid.plugin.CellEditing', {
    clicksToEdit: 2
});


var winDel;
var winAdd;
var enter;
var del;

var grid;
var gridCat;

var table_a_panel;
var table_b_panel;


/**
 * Performs control on passed value
 * 
 * @param value The value to check
 */
function check(value){
	if(!isNaN(parseInt(value)) && value >=0)
		return true;
	else
		return false;
}

Ext.onReady(function() {
    Ext.QuickTips.init();
    
    /**
     * Define insert form for table 1_4_a
     */
	enter = Ext.widget({
	  xtype: 'form',
	  layout: 'form',
	  id: 'forestEnter',
	  url: 'forestEnter',
	  frame: true,
	  title: 'Insert new entry in the DB for this user',
	  bodyPadding: '15 15 10',
	  width: 350,
	  fieldDefaults: {
	      msgTarget: 'side',
	      labelWidth: 75
	  },
	  defaultType: 'textfield',
	  items: [{
	  	id:'year',
	      fieldLabel: 'Year',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  },{
	  	id:'forest',
	      fieldLabel: 'Forest',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  },{
	  	id:'other_wooded_land',
	      fieldLabel: 'Other Wooded Land',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  },{
	  	id:'other_land',
	      fieldLabel: 'Other Land',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  },{
	  	id:'other_tree_cover',
	      fieldLabel: 'Other Tree Cover',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  },{
	  	id:'inland_water_bodies',
	      fieldLabel: 'Inland Water Bodies',
	      afterLabelTextTpl: required,
	      name: 'userid',
	      allowBlank:false
	  }],
	
	  buttons: [{
	      text: 'Submit',
	      handler: function(){
	      	if(check(Ext.getCmp('year').getValue())){
	          	Ext.Ajax.request({
	       		   url: 'forestEnter?'+
	       		   'year='+Ext.getCmp('year').getValue()+
	       		   '&forest='+Ext.getCmp('forest').getValue()+
	       		   '&owl='+Ext.getCmp('other_wooded_land').getValue()+
	       		   '&ol='+Ext.getCmp('other_land').getValue()+
	       		   '&otc='+Ext.getCmp('other_tree_cover').getValue()+
	       		   '&iwb='+Ext.getCmp('inland_water_bodies').getValue(),
	       		   success: function(response, opts) {
		         			   if(response.responseText.indexOf("formLogin") != -1){
			     			      window.location.href = "login.html";
		         			   }
	       			   enter.getForm().reset();
	       			   loadTable1_4_a();
	       			   if (!Ext.isIE7 && !Ext.isIE8) {
			       			new Ext.ux.Notification({
			                    title:      'Feedback',
			                    html:      'New data stored in database',
			                    autoDestroy: true
			                }).show(document);
	       			   }
	       		   },
	       		   failure: function(response, opts) {
	       		      enter.getForm().reset();
	       		      if (!Ext.isIE7 && !Ext.isIE8) {
			       		   new Ext.ux.Notification({
			                   title:      'Feedback',
			                   html:      'Data not stored in database',
			                   autoDestroy: true
			               }).show(document);
	       		      }
	       		   }
	          	});
	      	}
	      	else{
	      		if (!Ext.isIE7 && !Ext.isIE8) {
		      		new Ext.ux.Notification({
		                title:      'Feedback',
		                html:      'Field year not correct',
		                autoDestroy: true
		            }).show(document);
	      		}
	      	}
	      }
	  }
	  ,{
	      text: 'Reset',
	      handler: function(){
	      	enter.getForm().reset();
	      }
	  },{
		  text: 'Close',
		  handler: function(){
		    if (winAdd.isVisible()) {
	            winAdd.hide(this, function() {
	            });
	        }
		  }
	  }]
	});
	
	/**
     * Define the delete form for table 1_4_a 
     */
	del = Ext.widget({
        xtype: 'form',
        layout: 'form',
        id: 'forestDelete',
        url: 'forestDelete',
        frame: true,
        title: 'Delete an entry in the DB for this user',
        bodyPadding: '15 15 10',
        width: 350,
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        defaultType: 'textfield',
        items: [
          {
        	id:'year-del',
            fieldLabel: 'Year',
            afterLabelTextTpl: required,
            name: 'year',
            allowBlank:false
        }],

        buttons: [{
            text: 'Submit',
            handler: function(){
            	if(check(parseInt(Ext.getCmp('year-del').getValue()))){
	            	Ext.Ajax.request({
	         		   url: 'forestDelete?'+'year='+Ext.getCmp('year-del').getValue(),
	         		  success: function(response, opts) {
	         			   if(response.responseText.indexOf("formLogin") != -1){
		     			      window.location.href = "login.html";
	         			   }
	         			   del.getForm().reset();
	         			   loadTable1_4_a();
	         			  if (!Ext.isIE7 && !Ext.isIE8) {
		         			  new Ext.ux.Notification({
		      	                title:      'Feedback',
		      	                html:      'Deleted from the database',
		      	                autoDestroy: true
		      	            }).show(document);
	         			  }
	         		   },
	         		   failure: function(response, opts) {
	         		      enter.getForm().reset();
	         		     if (!Ext.isIE7 && !Ext.isIE8) {
		         		     new Ext.ux.Notification({
			      	                title:      'Feedback',
			      	                html:      'Not deleted from the database',
			      	                autoDestroy: true
			      	            }).show(document);
	         		     }
	         		   }
	            	});
            	}
            	else{
            		if (!Ext.isIE7 && !Ext.isIE8) {
	            		new Ext.ux.Notification({
	    	                title:      'Feedback',
	    	                html:      'Field year not correct',
	    	                autoDestroy: true
	    	            }).show(document);
            		}
            	}
            }
        }
        ,{
            text: 'Reset',
            handler: function(){
            	del.getForm().reset();
            }
        },{
		  text: 'Close',
		  handler: function(){
		    if (winDel.isVisible()) {
	            winDel.hide(this, function() {
	            });
	        }
		  }
	  }]
    });
    
	loadTable1_4_a();
	loadTable1_4_b();
	
	table_a_panel = new Ext.Panel({
        border: false,
        padding: 10,
        items: [grid]
    });
	table_b_panel = new Ext.Panel({
        border: false,
        padding: 10,
        items: [gridCat]
    });
	
		
	Ext.Ajax.request({
 		   url: 'getUsername',
 		   success: function(response, opts) {
 			  var viewport = new Ext.Viewport({
 					autoScroll: true,
 					minWidth: 600,
 			        minHeight: 400,
 			        renderTo: Ext.getBody(),
 			        layout:'border',
 			        items:[{
 			            region:'center',
 			            id:'centerArea',
 			            width: 200,
 			            minSize: 175,
 			            maxSize: 400,
 			            autoScroll: true,
 			            items: [table_a_panel,
 			                    table_b_panel]
 			        },{
 			        	title:'Banner area',
 			        	region: "north",
 			        	xtype: 'toolbar',
 			        	items: [{
 			        		xtype: 'button',
 			        		text: 'Logout ' + response.responseText,
 			        		icon: 'logout.png',
 			        		handler: function(btn){
 			        			Ext.Ajax.request({
 			   	         		   url: 'logout',
 			   	         		   success: function(response, opts) {
 			   	         			   window.location.href = "login.html";
 			   	         		   },
 			   	         		   failure: function(response, opts) {
 			   	         			   window.location.href = "login.html";
 			   	         		   }
 			   	         	  });
 			        		}
 			        	}]
 			        },{
 			            region:'south',
 			            id:'southArea',
 			            xtype: 'toolbar',
 			        	items: [{
 			        		xtype: 'button',
 			        		icon: 'favicon.ico',
 			        		text: 'Powered by GeoSolutions',
 			        		handler: function(btn){
 			        			window.open('http://www.geo-solutions.it/','mywindow','width=400,height=200,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes, resizable=yes');
 			   	         	}
 			        	}]
 			        }]
 			    });
 		   },
 		   failure: function(response, opts) {
 		   }
 	  });

});


/**
 * Function called in order to create the table 1_4_a
 */
function loadTable1_4_a(){
	
	/**
     * Define the model for table 1_4_a data
     */
	Ext.define('entity', {
	    extend: 'Ext.data.Model',
	    fields: [
	        {name: 'year',  type: 'int'},
	        {name: 'forest',   type: 'string'},
	        {name: 'other_wooded_land', type: 'string'},
	        {name: 'other_land', type: 'string'},
	        {name: 'other_tree_cover',  type: 'string'},
	        {name: 'inland_water_bodies',   type: 'string'},
	        {name: 'userid', type: 'string'}
	    ]
	});

	/**
     * Define the store for table 1_4_a data
     */
	var store = new Ext.data.Store({
		storeId:'userdata',
	    model: 'entity',
	    proxy: {
	        type: 'ajax',
	        url : 'forestUser',
	        reader: {
	            type: 'json'
	        }
	    }
	});
	store.load({
		    callback: function (records, operation, success) {
		    	if(operation.response == undefined)
		    		window.location.href = "login.html";
		    }
	    });
	store.sort('year', 'ASC');
	
	/**
     * Callback function called when the store is ready
     */
	store.on('load', function() {
		table_a_panel.remove(grid,false);
		// ////////////////////////////////////////////////////////
		// in this function it's necessary to "convert" the store
		// because new data in the table means new COLUMN!!!
		// ///////////////////////////////////////////////////////
		
		
		// ///////////////////////////////////////////////////////////////////////
		// Prepare fields for Model:
		//     every field is named with an integer from 0 to store.getCount()
		//     because the number of columns depends from the number of entries
		//     for this user in the DB (dinamic)
		// ///////////////////////////////////////////////////////////////////////
		var fields = new Array(store.getCount());
		for(var i = 0; i < store.getCount()+1; i++){
			var field = {name: ''+i, type: 'string'};
			fields[i]=field;
		}
		
		/**
	     * Define the dynamic model for table 1_4_a data
	     */
		Ext.define('userModel', {
		    extend: 'Ext.data.Model',
		    fields: fields
		});
		
		/**
	     * Define the dynamic store for table 1_4_a data
	     */
		var userStore = new Ext.data.Store({
			storeId:'userStore',
		    model: 'userModel'
		});
		
		// ///////////////////////////////////////////////////////////////////////
		// Fill the store
		//     there are always 5 records
		// ///////////////////////////////////////////////////////////////////////
		var arr = [];
		var temp = "forest";
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("forest");
			arr[i+1]=temp;
		}
		userStore.add([arr]);
		var temp = 'other_wooded_land';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_wooded_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);
		var temp = 'other_land';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);
		var temp = 'other_tree_cover';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_tree_cover");
			arr[i+1]=temp;
		}
		userStore.add([arr]);
		var temp = 'inland_water_bodies';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("inland_water_bodies");
			arr[i+1]=temp;
		}
		userStore.add([arr]);
		var temp = 'TOTAL';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			tmp1 = store.getAt(i).get("forest") == 'n.a.' ? 0 : parseFloat(store.getAt(i).get("forest"));
			tmp2 = store.getAt(i).get("other_tree_cover") == 'n.a.' ? 0 : parseFloat(store.getAt(i).get("other_tree_cover"));
			tmp3 = store.getAt(i).get("other_land") == 'n.a.' ? 0 : parseFloat(store.getAt(i).get("other_land"));
			tmp4 = store.getAt(i).get("other_wooded_land") == 'n.a.' ? 0 : parseFloat(store.getAt(i).get("other_wooded_land")); 
			tmp5 = store.getAt(i).get("inland_water_bodies") == 'n.a.' ? 0 : parseFloat(store.getAt(i).get("inland_water_bodies"));
			arr[i+1]=tmp1+tmp2+tmp3+tmp4+tmp5;
		}
		userStore.add([arr]);
		
		// ///////////////////////////////////////////////////////////////////////
		// Prepare dinamic columns
		// ///////////////////////////////////////////////////////////////////////
		var cols = new Array(store.getCount());
		for(var i = 0; i < store.getCount(); i++){
			var col={ width : 50, sortable : false, menuDisabled:true, dataIndex : ''+(i+1), text : store.getAt(i).get("year"), editor:{allowBlank: false}};
			cols[i] = col;
		}
		
//		if (document.getElementById('grid-div').hasChildNodes())
//			document.getElementById('grid-div').removeChild(document.getElementById('grid-div').childNodes[0]);
		
		/**
	     * Define the dynamic grid for table 1_4_a data
	     */
		grid = new Ext.grid.GridPanel({
			tbar: [
			       {   xtype: 'button',
			    	   text: 'Add column',
			    	   icon: 'add.png',
			    	   handler: function() {
			    		   if (!winAdd) {
			    	    		winAdd = Ext.create('widget.window', {
			    	                title: 'Add Window',
			    	                closable: true,
			    	                closeAction: 'hide',
			    	                width: 400,
			    	                minWidth: 350,
			    	                minHeight: 337,
			    	                height: 337,
			    	                layout: {
			    	                    type: 'border',
			    	                    padding: 5
			    	                },
			    	                items: [enter]
			    	            });
			    	    	}
			    	    	if (winAdd.isVisible()) {
			    	            winAdd.hide(this, function() {
			    	            });
			    	        } else {
			    	            winAdd.show(this, function() {
			    	            });
			    	        }
			    	   }
			       },
			       {
			    	    xtype: 'button',
			            text: 'Delete column',
			            icon: 'delete.png',
			            handler: function() {
			            	if (!winDel) {
			            		winDel = Ext.create('widget.window', {
			                        title: 'Delete Window',
			                        closable: true,
			                        closeAction: 'hide',
			                        width: 400,
			                        minWidth: 350,
			                        height: 160,
			                        minHeight: 160,
			                        layout: {
			                            type: 'border',
			                            padding: 5
			                        },
			                        items: [del]
			                    });
			            	}
			            	if (winDel.isVisible()) {
			                    winDel.hide(this, function() {
			                    });
			                } else {
			                    winDel.show(this, function() {
			                    });
			                }
			            }
			       }
			     ],
		    title: 'User data (put \"n.a.\" if you want a null value in db)',
		    store: userStore,
		    columns: [
				{ text:'FRA 2015 categories', dataIndex: '0', width: 110, sortable : false, menuDisabled:true},
				{ text: 'Area (1000 hectares)', columns: cols, sortable : false, menuDisabled:true}
			],
		    selModel: {
	            selType: 'cellmodel'
	        },
	        plugins: [cellEditing],
		    height: 250,
		    width: 602,
		    style: 'margin:0 auto;'
		    
		});
		
		/**
	     * Callback function called when a cell of the grid is ready
	     */
		grid.on('edit', function(editor, e) {
			
			  if (e.rowIdx == 5 || e.value == e.originalValue || (!check(e.value) && e.value != 'n.a.')) {
			      e.record.data[e.field] = e.originalValue;
			      e.record.commit();
			      if(e.rowIdx == 5){
			    	  if (!Ext.isIE7 && !Ext.isIE8) {
				    	  new Ext.ux.Notification({
			   	                title:      'Feedback',
			   	                html:      'Field TOTAL is not editable.',
			   	                autoDestroy: true
			   	            }).show(document);
			    	  }
			      }
			      else if(e.value == e.originalValue){
			    	  if (!Ext.isIE7 && !Ext.isIE8) {
				    	  new Ext.ux.Notification({
			   	                title:      'Feedback',
			   	                html:      'Field not modified.',
			   	                autoDestroy: true
			   	            }).show(document);
			    	  }
			      }
			      else if(!check(e.value) && e.value != 'n.a.'){
			    	  if (!Ext.isIE7 && !Ext.isIE8) {
				    	  new Ext.ux.Notification({
			   	                title:      'Feedback',
			   	                html:      'Wrong value.',
			   	                autoDestroy: true
			   	            }).show(document);
			    	  }
			      }
			      
			  }
			  else{
				  Ext.Ajax.request({
	         		   url: 'forestUpdate?'+
	         		   'year='+e.column.text+
	         		   '&param='+e.record.data[0]+
	         		   '&value='+e.value,
	         		   success: function(response, opts) {
	         			   if(response.responseText.indexOf("formLogin") != -1){
	         				  e.cancel = true;
		     			      e.record.data[e.field] = e.originalValue;
		     			      e.record.commit();
		     			      window.location.href = "login.html";
	         			   }
	         			   var rec = userStore.getAt(5);
	         			   var value = parseFloat(userStore.getAt(5).get(e.colIdx));
	         			   if(e.value == 'n.a.')
	         				   value = value - e.originalValue;
	         			   if(e.originalValue == 'n.a.')
	         				   value = value + parseFloat(e.value);
	         			   if(e.value != 'n.a.' && e.originalValue != 'n.a.')
	         				   value = value - parseFloat(e.originalValue) + parseFloat(e.value);
	         			   rec.set(''+e.colIdx, ''+value);
	         			   userStore.commitChanges();
	         			  if (!Ext.isIE7 && !Ext.isIE8) {
		         			  new Ext.ux.Notification({
		         	                title:      'Feedback',
		         	                html:      'Updated in database',
		         	                autoDestroy: true
		         	            }).show(document);
	         			  }
	         		   },
	         		   failure: function(response, opts) {
	         		      e.cancel = true;
	     			      e.record.data[e.field] = e.originalValue;
	     			      e.record.commit();
	     			     if (!Ext.isIE7 && !Ext.isIE8) {
		     			     new Ext.ux.Notification({
		     	                title:      'Feedback',
		     	                html:      'Not updated in database',
		     	                autoDestroy: true
		     	            }).show(document);
	     			     }
	         		   }
	         	  });
			  }
		});
		
		table_a_panel.add(grid);
		table_a_panel.doLayout();
	});	
	
}


/**
 * Function called in order to create the table 1_4_b
 */
function loadTable1_4_b(){
	/**
     * Define the model for table 1_4_b
     */
	Ext.define('category', {
	    extend: 'Ext.data.Model',
	    fields: [
	        {name: 'category',   type: 'String'},
	        {name: 'tier_for_reported_trend', type: 'String'},
	        {name: 'tier_for_status', type: 'String'}
	    ]
	});
	
	/**
     * Define the store for table 1_4_b 
     */
	var storeCategories = new Ext.data.Store({
		storeId:'storeCategories',
	    model: 'category',
	    proxy: {
	        type: 'ajax',
	        url : 'categoriesUser',
	        reader: {
	            type: 'json'
	        }
	    }
	});
	storeCategories.load({
	    callback: function (records, operation, success) {
	    	if(operation.response == undefined)
	    		window.location.href = "login.html";
	    }
    });
	
	/**
     * Callback function called when the store is ready
     */
	storeCategories.on('load', function() {
		
		/**
	     * Define the grid for table 1_4_b
	     */
		gridCat = new Ext.grid.GridPanel({
		    title: 'Categories',
		    store: storeCategories,
		    columns: [
				{ 
					sortable : false,
					menuDisabled:true,
					text:'Variable/category',
					dataIndex: 'category',
					width: 200
				},
				{ 
					sortable : false,
					menuDisabled:true,
					id: 'tier_for_reported_trend',
					text: 'Tier for reported trend',
					dataIndex: 'tier_for_reported_trend',
					editor:{allowBlank: false},
					editor: new Ext.form.field.ComboBox({
						fieldLabel: 'Choose Tier',
		                typeAhead: true,
		                triggerAction: 'all',
		                selectOnTab: true,
		                store: [
		                    ['Tier 1','Tier 1'],
		                    ['Tier 2','Tier 2'],
		                    ['Tier 3','Tier 3'],
		                    ['n.a.','n.a.']
		                ],
		                lazyRender: true,
		                listClass: 'x-combo-list-small'
		            }),
		            width: 200
				},
	            { 
					sortable : false,
					menuDisabled:true,
					text: 'Tier for status',
					id: 'tier_for_status',
					dataIndex: 'tier_for_status',
					editor: new Ext.form.field.ComboBox({
						fieldLabel: 'Choose Tier',
		                typeAhead: true,
		                triggerAction: 'all',
		                selectOnTab: true,
		                store: [
		                    ['Tier 1','Tier 1'],
		                    ['Tier 2','Tier 2'],
		                    ['Tier 3','Tier 3'],
		                    ['n.a.','n.a.']
		                ],
		                lazyRender: true,
		                listClass: 'x-combo-list-small'
		            }),
		            width: 200
				}
			],
		    selModel: {
	            selType: 'cellmodel'
	        },
	        plugins: [cellEditing1],
		    height: 117,
		    width: 602,
		    style: 'margin:0 auto;'
		});
		
		/**
	     * Callback function called when a cell of the grid is edited
	     */
		gridCat.on('edit', function(editor, e) {
			
			if (e.value != "Tier 1" && e.value != "Tier 2" && e.value != "Tier 3" && e.value != "n.a.") {
				if (!Ext.isIE7 && !Ext.isIE8) {
					new Ext.ux.Notification({
	  	                title:      'Feedback',
	  	                html:      'Select a value contained in the combobox',
	  	                autoDestroy: true
	  	            }).show(document);
				}
			    e.record.data[e.field] = e.originalValue;
			    e.record.commit();
			}
			else{
				if(e.value != e.originalValue){
					Ext.Ajax.request({
		         		   url: 'categoriesUpdate?'+
		         		   'param='+e.column.id+
		         		   '&category='+e.record.data['category']+
		         		   '&value='+e.value,
		         		   success: function(response, opts) {
		         			   if(response.responseText.indexOf("formLogin") != -1){
			     			      window.location.href = "login.html";
		         			   }
		         			  storeCategories.commitChanges();
		         			 if (!Ext.isIE7 && !Ext.isIE8) {
			         			 new Ext.ux.Notification({
				      	                title:      'Feedback',
				      	                html:      'Updated in database',
				      	                autoDestroy: true
				      	            }).show(document);
		         			 }
		         		   },
		         		   failure: function(response, opts) {
		         		      e.cancel = true;
		     			      e.record.data[e.field] = e.originalValue;
		     			      e.record.commit();
		     			     if (!Ext.isIE7 && !Ext.isIE8) {
			     			     new Ext.ux.Notification({
				      	                title:      'Feedback',
				      	                html:      'Not updated in database',
				      	                autoDestroy: true
				      	            }).show(document);
		     			     }
		         		   }
		         	});
			    }
			}
		});
		table_b_panel.add(gridCat);
		table_b_panel.doLayout();
	});
}
























