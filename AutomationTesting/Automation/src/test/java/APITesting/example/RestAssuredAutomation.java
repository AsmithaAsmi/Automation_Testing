package APITesting.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RestAssuredAutomation {
    public static void main(String[] args) throws JSONException {
        RestAssuredAutomation assuredAutomation = new RestAssuredAutomation();
        assuredAutomation.createUser();
        assuredAutomation.getBooks();
        assuredAutomation.addBooks("TestBookName", assuredAutomation.createUser());
    }

    public String createUser() throws JSONException {
        RestAssured.baseURI = "https://demoqa.com/Account/v1/User";
        RequestSpecification requestSpecification = RestAssured.given();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "testName1");
        jsonObject.put("password", "testPass@1");
        requestSpecification.headers("Content-Type", "application/json");
        requestSpecification.headers("accept", "application/json");
        requestSpecification.body(jsonObject.toString());
        Response response = requestSpecification.post();
        System.out.println("Response is : ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JSONObject object = new JSONObject(response.getBody().asString());
        String userId = object.getString("userID");
        return userId;
    }

    public void getBooks(){
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.headers("accept", "application/json");
        Response response = requestSpecification.get();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    public void addBooks(String bookName, String userId) throws JSONException {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification requestSpecification = RestAssured.given();
        JSONObject payload = new JSONObject();
        JSONArray collectionOfIsbns = new JSONArray();
        JSONObject bookObj = new JSONObject();
        bookObj.put("isbn", bookName);
        collectionOfIsbns.put(bookObj);
        payload.put("userId", userId);
        payload.put("collectionOfIsbns", collectionOfIsbns);
        requestSpecification.headers("accept", "application/json");
        requestSpecification.body(payload);
        Response response = requestSpecification.post();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
