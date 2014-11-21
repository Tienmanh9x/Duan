package xml.parse;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class ParseXml {

	private URL feedUrl;
    private TinTuc entry;
	final String RSS = "rss";
	final String CHANNEL = "channel";
	final String ITEM = "item";
	final String TITLE = "title";
	final String DESCRIPTION = "description";
	final String LINK = "link";
	final String PUBDATE = "pubDate";
	final String CDATA = "image";

	String tag = "xml parser";
	int i = 1;

	//

	public ParseXml(String feedUrlString) {
		try {
			// get url
			Log.i("URL: ", feedUrlString);
			this.feedUrl = new URL(feedUrlString);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ListTinTuc parseXMLTinTuc() {
		final ListTinTuc listItemEntry = new ListTinTuc();
		entry = new TinTuc();

		RootElement root = new RootElement(RSS);
		// element root rss
		Element channel = root.getChild(CHANNEL);
		Element item = channel.getChild(ITEM);

		item.getChild(TITLE).setEndTextElementListener(
				new EndTextElementListener() {
					@Override
					public void end(String body) {
						Log.i(tag, "============//================");
						Log.i(tag, "item: " + i);
						i++;
						Log.i(tag, "Title: " + body);
						entry.setTitle(body);
					}
				});
		item.getChild(LINK).setEndTextElementListener(
				new EndTextElementListener() {
					@Override
					public void end(String body) {
						Log.i(tag, "============//================");
						Log.i(tag, "item: " + i);
						i++;
						Log.i(tag, "LINK: " + body);
						entry.setLink(body);
					}
				});
		item.getChild(CDATA).setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String body) {
				Log.i(tag, "Image: " + body);
				entry.setImage(body);			
			}
		});
		
		item.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				// copy tung item vao object Entry
				Log.i(tag, "============//================");
				Log.i(tag, "============Entry Copy================");
				listItemEntry.setListTinTuc(entry.tintucCopy());
			}
		});

		// get item entry
		root.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				Log.i(tag, "============//================");
				Log.i(tag, "============listEntryCopy================");
				// khi lay dc toan bo du lieu no se copy sang 1 list khac va du
				// lieu duoc xu ly tren du lieu tam nay
				listItemEntry.listTinTucCopy();
			}
		});

		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8,
					root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listItemEntry;
	}
}
