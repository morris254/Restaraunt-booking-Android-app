package com.restaurant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class OrderList extends Activity {
	  ArrayAdapter <String> adapter ;
	  ModelClass orderModelClass;
	  ListView list;
	  static String s1;
	  static String s2;
		String user_name;

	  Button gotomenu,placedorder;
	  DatabaseHelper orderHelper;
	  static String s;
	  String cust_name,phone,email,order,address;
	  
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.orderlist);
	       //initialise list view to dispay data in list
	        
	        orderModelClass = new ModelClass();
	        list=(ListView)findViewById(R.id.list);
	        gotomenu = (Button)findViewById(R.id.gotomenu_btn);
	        placedorder =(Button)findViewById(R.id.PlaceOrder_btn);
	        
	        
	      //  orderModelClass = orderHelper.getSingleInfo(LoginActivity.KY_PHONE);
	        
	        
	        //System.out.println(mClass.getPhone());
	        cust_name =orderModelClass.getName();
	        phone = orderModelClass.getPhone();
	        email=orderModelClass.getEmail();
	        address =orderModelClass.getAddress();
	        
	      adapter=new ArrayAdapter<String>(OrderList.this,
	            android.R.layout.simple_list_item_1,
	            ModelClass.al);
	        list.setAdapter(adapter);
	        if(ModelClass.al.isEmpty())
	        {
	        	Toast.makeText(this, "Yet no order Is placed Please go to menu section and add order", Toast.LENGTH_LONG).show();
	        	
	        }
	        else{
	        	s2 = ModelClass.al.get(0).toString();
	        }
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0,
						View view, final int position,
						long id) {
					// TODO Auto-generated method stub
					
					//order remove functioanlity on alert box button
					AlertDialog.Builder builder =new AlertDialog.Builder(OrderList.this);
					builder.setTitle("Order Remove");
					builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							s=(String)list.getItemAtPosition(position);
							ModelClass.al.remove(s);
							adapter.notifyDataSetChanged();
						}
					});
					builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					AlertDialog alt=builder.create();
					alt.show();
				}
			});
	        gotomenu.setOnClickListener(new OnClickListener() {
		
	        	@Override
	        	public void onClick(View v) {
	        		Intent menuIntent = new Intent(OrderList.this,MenuScreen.class);
	        		startActivity(menuIntent);
	        	}
	        });  
	        for(int i=1 ;i<ModelClass.al.size();i++){
	        	 s1 = ModelClass.al.get(i).toString();
	        	 s2 += "," + s1;
	        }
	        
			
	      placedorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = phone.toString();
				String sms = cust_name.toString();

				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(number, null,"Congrats "+ sms+"Your Order is succesful! for more details about us visit our store find us in find store button in our app", null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS faild, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
	      });

	 }
	 
	 
	 }

		
	      
