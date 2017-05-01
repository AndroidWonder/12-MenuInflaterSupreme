/* Context menus and options menus may be built using xml definitions or programatically.
 * The /res/menu directory may contain one or many menus. Menus may have submenus also built with xml.
 * This example contains both an options menu and a context menu with a submenu.
 */

package com.course.example.menuinflater;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.widget.ImageView;

public class MenuInflaterActivity extends Activity {
	
	private ImageView img = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        img = (ImageView)findViewById(R.id.image);
        img.setImageResource(R.drawable.trek);
        
        registerForContextMenu(img);

		//show logo on action bar and hide title
		ActionBar actionBar = getActionBar();
		actionBar.setLogo(R.drawable.droid);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.menu, menu);
	    return true;
	}
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu2, menu);
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.web:
	        	uri = Uri.parse("http://www.bentley.edu");
	        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
	            return true;
	            
	        case R.id.maps:
	        	uri = Uri.parse("geo:0,0?q=175 Forest Street waltham ma");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	        	/*For API18 and 19 Google Maps is not on Launch Pad
             	 so should first check if Package is present to avoid app crashing.
             	 The API 23 emulator using the Intel instruction set would also crash here
             	 without first checking.
            	*/
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivity(intent);}
	            return true;   
	            
	        case R.id.dialer:
	        	uri = Uri.parse("tel:7818912000");
	        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
	            return true;    
	            
	        case R.id.email:
	        	uri = Uri.parse("mailto:hbentley@bentley.edu");
	        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
	            return true;    
	            
	        case R.id.save:
	        	            //noop
	            return true;    
	            
	        case R.id.exit:
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Uri uri;
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.web:
	        	uri = Uri.parse("http://www.bentley.edu");
	        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
	            return true;
	            
	        case R.id.maps:
	        	uri = Uri.parse("geo:0,0?q=175 Forest Street waltham ma");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivity(intent);}
	            return true;   
	            
	        case R.id.dialer:
	        	uri = Uri.parse("tel:7818912000");
	        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
	            return true;    
	            
	        case R.id.email:
	        	uri = Uri.parse("mailto:hbentley@bentley.edu");
	        	startActivity(new Intent(Intent.ACTION_SENDTO, uri));
	            return true;    
	            
	        case R.id.save:
	        	            //noop
	            return true;    
	            
	        case R.id.exit:
	        	finish();
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
}