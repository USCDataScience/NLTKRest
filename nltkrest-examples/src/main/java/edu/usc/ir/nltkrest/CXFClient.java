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
package edu.usc.ir.nltkrest;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;

public class CXFClient 
{
	
    public static void main( String[] args )
    {
        StringBuilder text= new StringBuilder();
        if (args.length > 0) {
	    for (String arg: args){
		text.append(arg);
		text.append(" ");
	    }
        }
	else{
	    System.err.println("CXFClient <text to be analyzed>");
	    System.err.println("Must provide text as arguments to this program for NER analysis.");
	    System.exit(2);
	}

	System.out.println("Performing NLTK analysis on text: "+text.toString());
        String result = getNER(text.toString());
        System.out.println(result);
    }
    
    public static String getNER(String text) {
    	String url = "http://localhost:8881/nltk";
	System.out.println("Connecting to NLTKRest at: ["+url+"]");
    	Response response = WebClient.create(url).put(text);
        String result = response.readEntity(String.class);
	return result;
    }
    
}
