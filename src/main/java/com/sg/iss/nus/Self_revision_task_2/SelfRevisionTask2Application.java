package com.sg.iss.nus.Self_revision_task_2;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import com.sg.iss.nus.Self_revision_task_2.constant.Constants;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@SpringBootApplication
public class SelfRevisionTask2Application implements CommandLineRunner {

	@Autowired
	@Qualifier(Constants.template02)
	private RedisTemplate<String, Object> template;

	private static final Logger logger = LoggerFactory.getLogger(SelfRevisionTask2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(SelfRevisionTask2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		FileInputStream fis = new FileInputStream(Constants.filePath);
		JsonReader jReader = Json.createReader(fis);
		JsonArray jArray = jReader.readArray();

		for (JsonValue jsonValue : jArray) {
			JsonObject jsonObject = jsonValue.asJsonObject();

			Map<String, Object> product = new HashMap<>();
			jsonObject.forEach((key, value) -> product.put(key, value.toString()));

			String productTitle = jsonObject.getString("title");

			template.opsForHash().put(Constants.REDIS_KEY, productTitle, product);
		}
		logger.info("Todos successfully loaded into Redis.");
	}
}
