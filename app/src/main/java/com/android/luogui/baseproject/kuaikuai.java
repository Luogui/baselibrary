/*
 * Copyright (c) 2018.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baseproject;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MultipartBody.Builder;

public class kuaikuai
{
	public static final String URL = "http://115.231.163.108:10232/upload";

	public static void main(String[] args)
	{
		boolean result = uploadToKuaikuai("C:\\Users\\luogui\\Desktop\\aaa.apk", "a/bbb.apk");
		System.out.println(result);
	}

	public static boolean uploadToKuaikuai(String fileSrc, String fileName)
	{
		String parent = fileName.substring(0, fileName.lastIndexOf("/") + 1);
		String filename = fileName.substring(fileName.lastIndexOf("/") + 1);
		String rs = "fail";
		//System.out.println(parent.length());
		//System.out.println(filename);

		try
		{
			System.out.println("http://115.231.163.108:10232/upload");
			System.out.println("filename:" + filename);
			System.out.println("parent:" + parent);
			System.out.println("fileSrc:" + fileSrc);
			rs = sendHttpPostRequest("http://115.231.163.108:10232/upload", filename, parent, fileSrc);
		}
		catch (Exception var6)
		{
			var6.printStackTrace();
			return false;
		}

		return rs.equals("success");
	}

	public static String sendHttpPostRequest(String serverUrl, String fileName, String parent, String fileSrc) throws Exception
	{
		OkHttpClient client = new OkHttpClient();
		RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), new File(fileSrc));
		Builder requestBodyBuilder = (new Builder()).setType(MultipartBody.FORM).addFormDataPart("file", fileName, fileBody).addFormDataPart("fileName", fileName).addFormDataPart("type", "5");
		if (parent != null && parent.trim().length() > 0)
		{
			requestBodyBuilder.addFormDataPart("parent", parent);
		}
		Request request = (new Request.Builder()).url(serverUrl).post(requestBodyBuilder.build()).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}