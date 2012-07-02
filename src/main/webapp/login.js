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

Ext.onReady(function() {
    Ext.QuickTips.init();

    var bd = Ext.getBody();

    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

    var simple = Ext.widget({
        xtype: 'form',
        layout: 'form',
        collapsible: true,
        id: 'login',
        url: 'login',
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
        	id:'username',
            fieldLabel: 'Username',
            afterLabelTextTpl: required,
            name: 'username',
            allowBlank:false
        },
        {
        	id:'password',
            fieldLabel: 'Password',
            afterLabelTextTpl: required,
            name: 'password',
            allowBlank:false
        }],

        buttons: [{
            text: 'Submit',
            handler: function(){
            	Ext.Ajax.request({
	         		   url: 'login?username='+Ext.getCmp('username').getValue()+
	         		   '&password='+Ext.getCmp('password').getValue(),
	         		   success: function(response, opts) {
	         			   if(response.status == 200)
	         				   alert("Login phase finish successfully! You will be redirected to your page");
	         			   	   window.location.href = "entryForm.html";
	         		   },
	         		   failure: function(response, opts) {
	         			   if(response.status == 401){
	         				   alert("Failure with code "+response.status+" username and password not present in DB");
	         				  simple.getForm().reset();
	         			   }
	         		   }
	            });
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