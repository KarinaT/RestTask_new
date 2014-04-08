import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.epam.mentoring.utils.JsonHelper;

public class TestSuite {

	@Test
	public static void Test() throws IOException, InterruptedException {
		String str = "testtext";
		System.out.println(JsonHelper.getJsonTemplate(str).toString());
		System.out.println("=================");
		JsonHelper.sendPostRequest(str);
		String response = JsonHelper.sendGetRequest(str);
		String value = null;
		JSONObject obj = new JSONObject(response);
		JSONObject collection = obj.getJSONObject("collection");
		JSONArray items = collection.getJSONArray("items");
		for (int i = 0; i < items.length(); i++) {
			JSONObject row = items.getJSONObject(i);
			JSONArray data = row.getJSONArray("data");
			for (int j = 0; j < data.length(); j++) {
				value = data.getJSONObject(j).getString("value");
				//if()
				System.out.println(value);
			}
		}

	}
}
