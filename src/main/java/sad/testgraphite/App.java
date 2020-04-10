package sad.testgraphite;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.billdesk.gc.GraphiteClient;
import com.billdesk.gc.GraphiteTimeSeries;
import com.billdesk.gc.SimpleGraphiteClient;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	static String URL = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException {
		BasicConfigurator.configure();
		// System.out.println("Hello world");
		testRemote();
		// System.out.println("sended");
		// testGetJson();
		// testGetData();
		// testQueryMetrics();
		// testGetAllMetrics();
		// testDeleteMetrices();
		// testRemoteAggregationSum();

	}

	public static void testRemote() throws InterruptedException {

		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		// int count = 0;

		// for (int k = 0; k < 5000; k++) {

		// String AlphaNumericString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		// String AlphaNumericString = "ABC123XYZ";

		// String key = null;
		int value = 0;
		// StringBuilder sb = new StringBuilder(10);
		// for (int i = 0; i < 10; i++) {

		// generate a random number between // 0 to AlphaNumericString variable
		// int index = (int) (AlphaNumericString.length() * Math.random());

		// add Character one by one in end of sb
		// sb.append(AlphaNumericString.charAt(index));
		// key = sb.toString();

		// }
		for (int i = 0; i < 4000; i++) {
			Random rand = new Random();
			value = rand.nextInt(30000);
			// client.sendMetric(key, value);
			// System.out.print("count: " + count++);
			// }
			// client.sendMetric("fold1.fold2.fold3.Rubina", 2356);

			// client.sendMetric("payments.source.biller.HDFC", value);
			client.sendMetric("payments.source.biller.HDFC", value);

			TimeUnit.SECONDS.sleep(1);

			value = rand.nextInt(10000);
			client.sendMetric("payments.source.biller.KTK", value);

			TimeUnit.SECONDS.sleep(1);

		}
		System.out.println("finished");
	}

//	public static void testRemote() {
//
//	      GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
//
//	      //client.sendMetric("anita1", 4711);
//	      List<String> metrics = client.getAllMetrics(); 
//	      if(metrics.contains("anita1")) {
//	    	  System.out.println("here is the value");
//	    	  client.deleteMetric("anita1");
//	    	  System.out.println("deleted");
//	      }
//	      else
//	    	  System.out.println("bad deal");
//	   }
	public static void testGetJson() throws IOException {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		String json = client.getChartJson("-24days", Arrays.asList("clientBrowser.browserType.Chrome.user.1",
				"clientBrowser.loginBrowser.Firefox.user.all"));

		logger.info(json);
	}

	public static void testGetData() throws IOException {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		client.sendMetric("chen0040.test.metric", 4711);
		logger.info("sended");
		List<GraphiteTimeSeries> data = client.getChartData("-1days", Arrays.asList("chen0040.test.metric"));
		logger.info("recieved");
		// logger.info("data: {}",
		// JSON.toJSONString(data,SerializerFeature.PrettyFormat));

		for (int i = 0; i < data.get(0).getPoints().size(); ++i) {
			logger.info("{} {}", data.get(0).getPoints().get(i).formatTime(),
					String.valueOf(data.get(0).getPoints().get(i).getValue()));
		}
	}

	public static void testQueryMetrics() throws IOException {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		String json = client.queryMetrics("chen0040.test");
		logger.info(json);
		json = client.queryMetrics("clientBrowser");
		logger.info(json);
	}

	public static void testGetAllMetrics() {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		List<String> metrics = client.getAllMetrics();
		logger.info("metrics: {}", metrics);
	}

	public static void testDeleteMetrices() {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
		/*
		 * List<String> metrics = client.getAllMetrics(); //
		 * System.out.println(metrics);
		 * 
		 * for (int i = 0; i < metrics.size(); i++) { String key = metrics.get(i); //
		 * System.out.println("key is "+key); logger.info(client.deleteMetric(key)); }
		 */

		client.sendMetric("hi", 8711);
		System.out.println("sended");

		/*
		 * List<String> metrics = client.getAllMetrics();
		 * 
		 * //logger.info("metrics: {}", metrics);
		 * 
		 * if(metrics.contains("hi"))
		 * 
		 * { logger.info(client.deleteMetric("hi"));
		 * 
		 * }
		 * 
		 * logger.info("deleted");
		 */
		// assertFalse(metrics.contains("chen0040.test.metric"));

	}

	public static void testRemoteAggregationSum() throws InterruptedException {
		GraphiteClient client = new SimpleGraphiteClient(URL, 2023);

		Random random = new Random(new Date().getTime());
		for (int i = 0; i < 18; ++i) {
			String domain = "chen0040";
			String measurement = "test";
			String instance = "aggregate-sum";
			int user = 1;

			int count1 = random.nextInt(100);
			client.sendMetric(domain + "." + measurement + "." + instance + ".user." + user, count1);

			user = 2;
			int count2 = random.nextInt(100);
			client.sendMetric(domain + "." + measurement + "." + instance + ".user." + user, count2);

			user = 3;
			int count3 = random.nextInt(100);
			client.sendMetric(domain + "." + measurement + "." + instance + ".user." + user, count3);

			Thread.sleep(10000L);

			logger.info("sending {} ...", count1 + count2 + count3);
			// System.out.println("sum is"+count1 + count2 + count3);
		}
	}

//    public static JsonNode query() throws UnknownHostException, SocketException {
//    	String graphiteHost = "localhost";
//    	QueryableGraphiteClient client = GraphiteClientFactory.queryableGraphiteClient(graphiteHost);
//
//    	String target = "constantLine(123.456)";
//
//    	JsonFormatter formatter = new JsonFormatter();
//    	GraphiteQueryBuilder<JsonNode> builder = new GraphiteQueryBuilder<>(formatter);
//    	GraphiteQuery<JsonNode> query = builder.setTarget(target).build();
//
//    	JsonNode results = client.query(query);
//    	return results;
//    }
}
