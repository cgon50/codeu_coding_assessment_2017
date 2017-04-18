// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

final class MyJSON implements JSON {
	
	private Map<String, JSON> jsMap;
	private Map<String, String> stringMap;
	
  public MyJSON(){
	  stringMap = new HashMap<String, String>();
	  jsMap = new HashMap<String, JSON>();  
  }
  //returns the JSON object associated with a string
  public JSON getObject(String name) {
	  if(name.length() < 2)
		  throw new IllegalArgumentException("Not a valid input");
	  
	return jsMap.get(name);
  }
  
  //essentially an add method, puts a new object in the map
  @Override
  public JSON setObject(String name, JSON value) {
	 jsMap.put(name, value);
     
	 return this;
  }
  
  //gets a string corresponding with a string
  @Override
  public String getString(String name) {
    // TODO: implement this
    return stringMap.get(name);
  }
  
  //adds to the string map
  @Override
  public JSON setString(String name, String value) {
    // TODO: implement this
	stringMap.put(name, value);
    return this;
  }
  //makes a deep copy of all the JSON objects in the JS map
  @Override
  public void getObjects(Collection<String> names) {
	  

		  names.addAll(jsMap.keySet());
	  
  }

  //makes a deep copy of all the strings into the collection names
  @Override
  public void getStrings(Collection<String> names) {
    // TODO: implement this
	 
		  names.addAll(stringMap.keySet());
	  
  }
}
