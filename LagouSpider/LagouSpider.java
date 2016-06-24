import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class LagouSpider {

	public static void main(String[] args) {
		String url = "http://www.lagou.com/jobs/positionAjax.json?px=default&first=true&kw=算法工程师&pn=1";
		try{
			URL lagou_url = new URL(url);
			URLConnection conn = lagou_url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((line = reader.readLine()) != null){
				line = new String(line.getBytes("utf-8"), "utf-8");
				builder.append(line);
			}
			JSONObject json_obj = new JSONObject(builder.toString());
			System.out.println(json_obj.get("content"));
			// Solve Chinese messy code:
			// Windows -> Preference -> General -> Content Types -> Text -> 
			// Java Source File -> Enter "utf-8" -> update -> OK
			FileWriter writer = new FileWriter("Lagou.txt");
			writer.write(json_obj.toString(4));
			reader.close();
			writer.close();			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
