/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uscir.NLTKRest_Client;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;
public class ClientDemo 
{
	
    public static void main( String[] args )
    {
        String text="";
        if (args.length > 0) {
        	text = args[0];
        }
        String result = getNER(text);
        System.out.println(result);
    }
    
    public static String getNER(String text) {
    	String url = "http://localhost:5000/nltk";
    	Response response = WebClient.create(url).form(new Form("text", text));
        String result = response.readEntity(String.class);
		return result;
    }
    
    public static int checkConnection() {
    	String url = "http://localhost:5000/index";
    	Response response = WebClient.create(url).get();
        int status = response.getStatus();
		return status;
    }
}
