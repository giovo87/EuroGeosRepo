Ext.require([
    //'Ext.form.*',
    //'Ext.layout.container.Column',
    //'Ext.tab.Panel'
    '*'
]);

//variable containing the current user
var user;
//for required fields
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
//flag: 0=not visible
//      1=visible
var formVisible = 0;

Ext.onReady(function() {
    Ext.QuickTips.init();

    //form for login
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
            	//get the current user
            	user = Ext.getCmp('userid').getValue();
            	loadTable1_4_a();
            	loadTable1_4_b();
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

//function for table 1.4.a
//function called when login fase is finished or every time the DB is modified
function loadTable1_4_a(){
	
	//remove the login form
	if (document.getElementById('login-div').hasChildNodes()) {
		document.getElementById('login-div').removeChild(document.getElementById('login-div').childNodes[0]);
	}
	
	//cell editing plugin
	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 2
    });
	
	//define the model to get the DB data
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
	
	//define the store to get the data
	var store = new Ext.data.Store({
		storeId:'userdata',
	    model: 'entity',
	    proxy: {
	        type: 'ajax',
	        url : 'forestUser?userid='+user,
	        reader: {
	            type: 'json'
	        }
	    }
	});
	//load the store with DB data
	store.load();
	//sort the store
	store.sort('year', 'ASC');
	//callback function called when store is ready
	store.on('load', function() {
		
		//////////////////////////////////////////////////////////
		// in this function it's necessary to "convert" the store
		// because new data in the table means new COLUMN!!!
		/////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////////////////
		// Prepare fields for Model:
		//     every field is named with an integer from 0 to store.getCount()
		//     because the number of columns depends from the number of entries
		//     for this user in the DB (dinamic)
		/////////////////////////////////////////////////////////////////////////
		var fields = new Array(store.getCount());
		for(var i = 0; i < store.getCount()+1; i++){
			var field = {name: ''+i, type: 'string'};
			fields[i]=field;
		}
		//set up the new model
		Ext.define('userModel', {
		    extend: 'Ext.data.Model',
		    fields: fields
		});
		//prepare the new store with the new model
		var userStore = new Ext.data.Store({
			storeId:'userStore',
		    model: 'userModel',
		});
		
		//fill the store
		var arr = [];
		var temp = "forest";
		arr[0] = temp;
		//get all forest values in the 1 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("forest");
			arr[i+1]=temp;
		}
		userStore.add([arr]); //add to the store
		var temp = 'other_wooded_land';
		arr[0] = temp;
		//get all owl values in the 2 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_wooded_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'other_land';
		arr[0] = temp;
		//get all ol values in the 3 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_land");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'other_tree_cover';
		arr[0] = temp;
		//get all otc values in the 4 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("other_tree_cover");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'inland_water_bodies';
		arr[0] = temp;
		//get all iwb values in the 5 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("inland_water_bodies");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		var temp = 'TOTAL';
		arr[0] = temp;
		//get all TOTALS values in the 6 record
		for(var i = 0; i < store.getCount(); i++){
			temp = store.getAt(i).get("forest") +
				store.getAt(i).get("other_tree_cover") +
				store.getAt(i).get("other_land") + 
				store.getAt(i).get("other_wooded_land") + 
				store.getAt(i).get("inland_water_bodies");
			arr[i+1]=temp;
		}
		userStore.add([arr]);//add to the store
		
		//prepare columns for grid (dinamic)
		var cols = new Array(store.getCount());
		for(var i = 0; i < store.getCount(); i++){
			var col={ width : 75, sortable : false, dataIndex : ''+(i+1), text : store.getAt(i).get("year"), editor:{allowBlank: false}};
			cols[i] = col;
		}
		
		//delete previous table
		if (document.getElementById('grid-div').hasChildNodes())
			document.getElementById('grid-div').removeChild(document.getElementById('grid-div').childNodes[0]);
		
		//prepare the grid
		var grid = new Ext.grid.GridPanel({
		    title: 'User data',
		    store: userStore,
		    columns: [
				{ text:'FRA 2015 categories', dataIndex: '0', width: 200},
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
		//callback function called every time a cell is edited
		grid.on('edit', function(editor, e) {
			  //if user try to change the TOTAL field OR not edit the value of a cell
			  if (e.rowIdx == 5 || e.value == e.originalValue) {
			      e.record.data[e.field] = e.originalValue;
			      e.record.commit();
			  }
			  //if a cell is edited with a new value
			  else{
				  //Ajax request: send user, year, name of the changed property and new value
				  Ext.Ajax.request({
	         		   url: 'forestUpdate?userid='+user+
	         		   '&year='+e.column.text+
	         		   '&param='+e.record.data[0]+
	         		   '&value='+e.value,
	         		   success: function() {
	         			   alert('Updated in db');
	         			   loadTable1_4_a(user);
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
		//display insert and delete form
		if(formVisible == 0){
			formVisible=1;
			showForm();
		}
	});
	
	
	
}

function showForm(){
	//prepare the insert form
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
         		   url: 'forestEnter?userid='+user+
         		   '&year='+Ext.getCmp('year').getValue()+
         		   '&forest='+Ext.getCmp('forest').getValue()+
         		   '&owl='+Ext.getCmp('other_wooded_land').getValue()+
         		   '&ol='+Ext.getCmp('other_land').getValue()+
         		   '&otc='+Ext.getCmp('other_tree_cover').getValue()+
         		   '&iwb='+Ext.getCmp('inland_water_bodies').getValue(),
         		   success: function() {
         			   alert('Add to the DB for user '+user);
         			   enter.getForm().reset();
         			   loadTable1_4_a(user);
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
	
	//prepare the delete form
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
         			   loadTable1_4_a(user);
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


function loadTable1_4_b(){
	
}
























