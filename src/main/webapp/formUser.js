Ext.require([
    //'Ext.form.*',
    //'Ext.layout.container.Column',
    //'Ext.tab.Panel'
    '*'
]);

var user;
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
var formVisible = 0;
var params = ["forest","owl","ol","otc","iwb"];

Ext.onReady(function() {
    Ext.QuickTips.init();

    var simple = Ext.widget({
        xtype: 'form',
        layout: 'form',
        collapsible: true,
        id: 'forestUser',
        url: 'forestUser',
        frame: true,
        title: 'Retrieve data user',
        bodyPadding: '15 15 10',
        width: 350,
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        defaultType: 'textfield',
        items: [{
        	id:'userid',
            fieldLabel: 'UserID',
            afterLabelTextTpl: required,
            name: 'userid',
            allowBlank:false
        }],

        buttons: [{
            text: 'Submit',
            handler: function(){
            	loadFromDb(Ext.getCmp('userid').getValue());
            }
        }
        ,{
            text: 'Cancel',
            handler: function(){
            	simple.getForm().reset();
            }
        }],
        renderTo: Ext.get('login-div')
    });

});

function loadFromDb(response){
	
	if (document.getElementById('login-div').hasChildNodes()) {
	    // It has at least one
		document.getElementById('login-div').removeChild(document.getElementById('login-div').childNodes[0]);
	}
	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 2
    });
	
	user = response;

	Ext.define('entity', {
	    extend: 'Ext.data.Model',
	    fields: [
	        {name: 'year',  type: 'int'},
	        {name: 'forest',   type: 'int'},
	        {name: 'other_wooded_land', type: 'int'},
	        {name: 'other_land', type: 'int'},
	        {name: 'other_tree_cover',  type: 'int'},
	        {name: 'inland_water_bodies',   type: 'int'},
	        {name: 'userid', type: 'string'}
	    ]
	});
	var store = new Ext.data.Store({
		storeId:'userdata',
	    model: 'entity',
	    proxy: {
	        type: 'ajax',
	        url : 'forestUser?userid='+response,
	        reader: {
	            type: 'json'
	        }
	    }
	});
	store.load();
	store.sort('year', 'ASC');

	
	store.on('load', function() {
		//prepare fields for Model
		var fields = new Array(store.getCount());
		for(var i = 0; i < store.getCount()+1; i++){
			var field = {name: ''+i, type: 'string'};
			fields[i]=field;
		}
		Ext.define('userModel', {
		    extend: 'Ext.data.Model',
		    fields: fields
		});
		//prepare the store
		var userStore = new Ext.data.Store({
			storeId:'userStore',
		    model: 'userModel',
		});
		
		//fill the store
		var arr = [];
		var temp = "forest";
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("forest");
			arr[i+1]=temp;
		}
		userStore.add([arr]); //add to the store
		var temp = 'other_wooded_land';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_wooded_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'other_land';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'other_tree_cover';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_tree_cover");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'inland_water_bodies';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("inland_water_bodies");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'TOTAL';
		arr[0] = temp;
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("forest") +
				store.getAt(i).get("other_tree_cover") +
				store.getAt(i).get("other_land") + 
				store.getAt(i).get("other_wooded_land") + 
				store.getAt(i).get("inland_water_bodies");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		
		//prepare columns for grid
		var cols = new Array(store.getCount());
		for(var i = 0; i < store.getCount(); i++){
			var col={ width : 75, sortable : false, dataIndex : ''+(i+1), text : store.getAt(i).get("year"), editor:{allowBlank: false}};
			cols[i] = col;
		}
		
		
		while (document.getElementById('grid-div').hasChildNodes())
			document.getElementById('grid-div').removeChild(document.getElementById('grid-div').childNodes[0]);
		//prepare the grid
		var grid = new Ext.grid.GridPanel({
		    title: 'User data',
		    store: userStore,
		    columns: [
				{ text:'FRA 2015 categories', dataIndex: '0'},
				{ text: 'Area (1000 hectares)', columns: cols}
			],
		    selModel: {
	            selType: 'cellmodel'
	        },
	        plugins: [cellEditing],
		    height: 200,
		    width: 910,
		    renderTo: Ext.get('grid-div')
		});
		
		grid.on('edit', function(editor, e) {
			  if (e.rowIdx == 5 || e.value == e.originalValue) {
			      e.record.data[e.field] = e.originalValue;
			      e.record.commit();
			  }
			  else{
				  Ext.Ajax.request({
	         		   url: 'forestUpdate?userid='+user+
	         		   '&year='+e.column.text+
	         		   '&param='+e.record.data[0]+
	         		   '&value='+e.value,
	         		   success: function() {
	         			   alert('Updated in db');
	         			   loadFromDb(user);
	         		   },
	         		   failure: function(response, opts) {
	         		      alert('Operation failed!');
	         		      e.cancel = true;
	     			      e.record.data[e.field] = e.originalValue;
	     			      e.record.commit();
	         		   }
	         	  });
			  }
			});
		if(formVisible == 0){
			formVisible=1;
			showForm();
		}
	});
}

function showForm(){
	var enter = Ext.widget({
        xtype: 'form',
        layout: 'form',
        collapsible: true,
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
        	id:'forest',
            fieldLabel: 'Forest',
            afterLabelTextTpl: required,
            name: 'userid',
            allowBlank:false
        },{
        	id:'year',
            fieldLabel: 'Year',
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
            	Ext.Ajax.request({
         		   url: 'forestEnter?userid='+user+'&year='+Ext.getCmp('year').getValue()+'&forest='+Ext.getCmp('forest').getValue()+'&owl='+Ext.getCmp('other_wooded_land').getValue()+'&ol='+Ext.getCmp('other_land').getValue()+'&otc='+Ext.getCmp('other_tree_cover').getValue()+'&iwb='+Ext.getCmp('inland_water_bodies').getValue(),
         		   success: function() {
         			   alert('Add to the DB for user '+user);
         			   enter.getForm().reset();
         			   loadFromDb(user);
         		   },
         		   failure: function(response, opts) {
         		      alert('Operation failed!');
         		      enter.getForm().reset();
         		   }
         	});
            }
        }
        ,{
            text: 'Cancel',
            handler: function(){
            	enter.getForm().reset();
            }
        }],
        renderTo: Ext.get('add-div')
    });
	
	
	var del = Ext.widget({
        xtype: 'form',
        layout: 'form',
        collapsible: true,
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
            	Ext.Ajax.request({
         		   url: 'forestDelete?userid='+user+'&year='+Ext.getCmp('year-del').getValue(),
         		   success: function() {
         			   alert('Delete from the DB for user '+user);
         			   del.getForm().reset();
         			   loadFromDb(user);
         		   },
         		   failure: function(response, opts) {
         		      alert('Operation failed!');
         		      enter.getForm().reset();
         		   }
         	});
            }
        }
        ,{
            text: 'Cancel',
            handler: function(){
            	enter.getForm().reset();
            }
        }],
        renderTo: Ext.get('delete-div')
    });
}
