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
package src.main.java.edu.usc.ir.visualization;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ner.NamedEntityParser;
import org.apache.tika.parser.ner.nltk.NLTKNERecogniser;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NLTKandCoreNLP {
    static FileWriter file;
    static int size;
    public static void main(String m[]) throws JsonParseException, JsonMappingException, IOException{
        HashSet<String> freq = new HashSet<String>();
        HashMap<String, Integer> nltk = new HashMap<String,Integer>();
        HashMap<String, Integer> nlp = new HashMap<String,Integer>();

        System.setProperty(NamedEntityParser.SYS_PROP_NER_IMPL, NLTKNERecogniser.class.getName());
        Metadata md;
        Tika t1=null;
        try {
            t1 = new Tika(new TikaConfig(new File("tika-config.xml")));
        } catch (TikaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String memexUrl = m[0];
        String username = m[1];
        String password = m[2];
        File destination =new File(m[3]);

        // create an ObjectMapper instance.
        ObjectMapper mapper = new ObjectMapper();
        // use the ObjectMapper to read the json string and create a tree

        JsonNode node;
        JsonNode dataset=null;
        for(int c=1; c<200; c+=100)
        {

            String url = memexUrl + "/select?q=gunsamerica&start="+c+"&rows=100&fl=content%2Corganizations%2Cpersons%2Cdates%2Clocations&wt=json&indent=true";
            Response response = WebClient.create(url, username, password, null).accept(MediaType.APPLICATION_JSON).get();
            String resp = response.readEntity(String.class);


            try {
                node = mapper.readTree(resp);
                dataset= node.get("response").get("docs");
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Iterator<JsonNode> datasetElements = dataset.iterator();
            while (datasetElements.hasNext()) {
                JsonNode datasetElement = datasetElements.next();
                String content = datasetElement.get("content").asText();
                md = new Metadata();
                try (InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
                    t1.parse(stream, md);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(datasetElement.has("locations")){
                    String names[]=null;
                    names = mapper.readValue(datasetElement.get("locations").toString(),String[].class);
                    for(int i=0; i<names.length; i++){
                        if(!freq.contains(names[i])){
                            freq.add(names[i]);
                        }

                        if(nlp.containsKey(names[i])){
                            nlp.put(names[i], nlp.get(names[i]) + 1);
                        }
                        else{
                            nlp.put(names[i], 1);
                        }
                    }
                }
                if(md.getValues("NER_NAMES").length > 0){
                    for(String ner_name: Arrays.asList(md.getValues("NER_NAMES"))){
                        if(!freq.contains(ner_name)){
                            freq.add(ner_name);
                        }
                        if(nltk.containsKey(ner_name)){
                            nltk.put(ner_name, nltk.get(ner_name) + 1);
                        }
                        else{
                            nltk.put(ner_name, 1);
                        }
                    }
                }


                if(datasetElement.has("dates")){
                    String names[]=null;
                    names= mapper.readValue(datasetElement.get("dates").toString(),String[].class);
                    for(int i=0; i<names.length; i++){
                        if(!freq.contains(names[i])){
                            freq.add(names[i]);
                        }
                        if(nlp.containsKey(names[i])){
                            nlp.put(names[i], nlp.get(names[i]) + 1);
                        }
                        else{
                            nlp.put(names[i], 1);
                        }
                    }

                }

                if(datasetElement.has("organizations")){
                    String names[]=null;
                    names= mapper.readValue(datasetElement.get("organizations").toString(),String[].class);
                    for(int i=0; i<names.length; i++){
                        if(!freq.contains(names[i])){
                            freq.add(names[i]);
                        }
                        if(nlp.containsKey(names[i])){
                            nlp.put(names[i], nlp.get(names[i]) + 1);
                        }
                        else{
                            nlp.put(names[i], 1);
                        }
                    }
                }

                if(datasetElement.has("persons")){
                    String names[]=null;
                    names= mapper.readValue(datasetElement.get("persons").toString(),String[].class);
                    for(int i=0; i<names.length; i++){
                        if(!freq.contains(names[i])){
                            freq.add(names[i]);
                        }
                        if(nlp.containsKey(names[i])){
                            nlp.put(names[i], nlp.get(names[i]) + 1);
                        }
                        else{
                            nlp.put(names[i], 1);
                        }
                    }
                }
            }
        }
        ArrayList<Names> frequencies = new ArrayList<Names>();
        for(String val:freq){
            int x=0;
            int y=0;
            if(nltk.containsKey(val)){
                x = nltk.get(val);
            }
            if(nlp.containsKey(val)){
                y = nlp.get(val);
            }
            frequencies.add(new Names(val, Math.abs(x-y) ));
        }
        Collections.sort(frequencies, compareByCount);
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Integer> nltk_value = new ArrayList<Integer>();
        ArrayList<Integer> nlp_value = new ArrayList<Integer>();
        for(int i=0; i<frequencies.size(); i++){
            String value = frequencies.get(i).name;
            labels.add(value);
            if(nltk.containsKey(value)){
                nltk_value.add(nltk.get(value));
            }
            else{
                nltk_value.add(0);
            }
            if(nlp.containsKey(value)){
                nlp_value.add(nlp.get(value));
            }
            else{
                nlp_value.add(0);
            }
        }
        Series nltk_json = new Series("nltk", nltk_value);
        Series nlp_json = new Series("nlp", nlp_value);
        Series []s = new Series[2];
        s[0] = nltk_json;
        s[1] = nlp_json;
        Labels final_json = new Labels(labels, s);
        ObjectMapper mymapper = new ObjectMapper();
        mymapper.writerWithDefaultPrettyPrinter().writeValue(new File(destination.getAbsolutePath() + "/nltk_vs_corenlp.json"), final_json);

    }

    public static Comparator<Names> compareByCount = new Comparator<Names>(){
        public int compare(Names one, Names two) {
            return (int)one.count - (int)two.count;
        }
    };

    static class Labels{
        ArrayList<String> labels;
        Series[] series;
        public Labels(ArrayList<String> labels, Series[] series) {
            this.labels = labels;
            this.series = series;
        }
        public ArrayList<String> getLabels() {
            return labels;
        }
        public void setLabels(ArrayList<String> labels) {
            this.labels = labels;
        }
        public Series[] getSeries() {
            return series;
        }
        public void setSeries(Series[] series) {
            this.series = series;
        }
    }

    static class Series{
        String name;
        ArrayList<Integer> value;
        Series(String name, ArrayList<Integer> value ){
            this.name=name;
            this.value=value;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public ArrayList<Integer> getValue() {
            return value;
        }
        public void setValue(ArrayList<Integer> value) {
            this.value = value;
        }
    }
    static class Names{
        String name;
        int count;
        Names(String x, int y){
            name=x;
            count=y;
        }
    }
}
