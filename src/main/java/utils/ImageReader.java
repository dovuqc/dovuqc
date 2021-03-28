package utils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.File;
public class ImageReader {
    public static String getText(String pathName) {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post("https://api.ocr.space/parse/image")
                    .field("filetype", "png")
                    .field("base64image", "data:image/png;base64")
                    .field("file", new File(pathName))
                    .field("apikey", "184007c35a88957")
                    .field("OCREngine", "2")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        Object result = response.getBody().getObject().getJSONArray("ParsedResults").get(0);
        String text = ((JSONObject) result).get("ParsedText").toString();
        text = StringUtils.remove(text, " ");
        System.out.println("Result is: " + text);
        return text;
    }

}
