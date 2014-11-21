package xml.custom;

import java.util.ArrayList;
import java.util.List;

import xml.imagedowloader.ImageLoader;
import xml.parse.TinTuc;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsexml_vnnet.R;

public class CustomView extends BaseAdapter {
	
	List<TinTuc> arrayTinTuc = new ArrayList<TinTuc>();
	Activity context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	public CustomView(Activity context, List<TinTuc> arrayTinTuc){
		super();
		this.context = context;
	    this.arrayTinTuc = arrayTinTuc;
	    imageLoader = new ImageLoader(context.getApplicationContext());
	    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayTinTuc.size();
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayTinTuc.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	class ViewHolder{
		ImageView imagehotel;
		TextView lbTitle;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        
		ViewHolder holder;
		
		if(convertView==null){
			holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.itemhotel,null);
            
            holder.imagehotel = (ImageView) convertView.findViewById(R.id.imagehotel);
            holder.lbTitle = (TextView) convertView.findViewById(R.id.lbTitle);
            
            convertView.setTag(holder);
		}
		
		else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		TinTuc tintuc = (TinTuc) arrayTinTuc.get(position);
		holder.lbTitle.setText(tintuc.getTitle());
		imageLoader.DisplayImage(tintuc.getImage(), holder.imagehotel);
		return convertView;
	}
}
