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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


final class MyJSONParser implements JSONParser {

	final static int isObject = Integer.MAX_VALUE;
	final static int isString = Integer.MIN_VALUE;
	@Override
	public JSON parse(String in) throws IOException {
		MyJSON js = new MyJSON();
		//count brackets make sure they match
		if(!syntaxOkay(in)){
			throw new IllegalStateException("Mismatched brackets error");
		}
		//get rid of spaces to make things easier
		String withoutSpaces = remove(in, ' ');
		//handles edge case of an empty object
		String withoutBraces = remove(withoutSpaces, '{');
		withoutBraces = remove(withoutBraces, '}');
		if(withoutBraces.length() == 0){
			return js;
		}
		int colonIndex = in.indexOf(":");
		String key = in.substring(0, colonIndex);
		String value = in.substring(colonIndex + 1);

		if(value.contains(":")){
			//this means the value is an object so we figure out how many strings to add to the object
			String[] values = value.split(",");
			//creates a temp for the new object
			MyJSON temp = new MyJSON();
			fillObject(values, temp);
			key = removeOutsides(key);
			js.setObject(key, temp);
		}else{
			//the base case that actually puts things as a JSON object
			key = removeOutsides(key);
			value = removeOutsides(value);
			js.setString(key, value);
		}

		return js;
	}
	//handles the case where the value given is itself an object
	private void fillObject(String[] values, MyJSON objectToBeFilled){
		for(int i = 0; i < values.length; i++){

			values[i] = remove(values[i], '{');
			values[i] = remove(values[i], '}');
			int colIndex = values[i].indexOf(":");

			String name = values[i].substring(0, colIndex);
			String val = values[i].substring(colIndex + 1, values[i].length());
			name = removeOutsides(name);
			val = removeOutsides(val);
			objectToBeFilled.setString(name, val);
		}

	}
	//makes a new string from within the quotation marks of the string sent in
	private String removeOutsides(String in){
		StringBuilder str = new StringBuilder();
		int firstQuote = in.indexOf("\"");
		int secondQuote = in.indexOf("\"", firstQuote + 1);
		for(int i = firstQuote + 1; i < secondQuote; i++){
			str.append(in.charAt(i));
		}

		return str.toString();
	}
	//removes all the spaces in a given string
	private String remove(String in, char toBeRemoved){
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < in.length(); i++){
			if(in.charAt(i) != toBeRemoved){
				str.append(in.charAt(i));
			}
		}
		return str.toString();
	}
	private String escapeCharacters(){
		return null;
	}

	//checks that all objects have matching brackets and quotes
	private boolean syntaxOkay(String in){
		int numOpenBraces = 0;
		int numCloseBraces = 0;
		int countQuote = 0;
		for(int i = 0; i < in.length(); i++){
			char currentChar = in.charAt(i);
			if(currentChar == '{'){
				numOpenBraces++;
			}else if(currentChar == '}'){
				numCloseBraces++;
			}else if(currentChar == '"'){
				countQuote++;
			}
		}
		return numOpenBraces == numCloseBraces && countQuote % 2 == 0;
	}

}
