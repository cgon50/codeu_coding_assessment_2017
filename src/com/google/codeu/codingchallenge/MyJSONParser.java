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
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

final class MyJSONParser implements JSONParser {


	@Override
	public JSON parse(String in) throws IOException {
		//count brackets make sure they match
		if(!bracketsOkay(in)){
			throw new illegalStateError("Mismatched brackets error");
		}
		//skip until next nonbrace - non space character
		int start = nonSpaceIndex(in, 0);
		//if the first charater isnt a quotation mark then bad syntax
		if(in.charAt(start) == '\\'){
			
		}
		if(!in.charAt(start) == "\""){
			 throw new illegalStateError("mismatched quotation mark");
		}
		//go past the quotation mark
		start = start + 1;
		String key = getKey(in, start);
		return new MyJSON();
	}
	private String escapeCharacters(){
		
	}
	private String getKey(String in, int index){
		StringBuilder key = new StringBuilder();
		for(int i = index; i < in.length(); i++){
			if(in.charAt(i) == "\""){
				break;
			}else{
				key.append(in.charAt(i));
			}
		}
		return key.toString();
	}
	//finds the next part of the string that is a non brace and non space
	private int nonSpaceIndex(String in, int index){
		for(int i = index; i < in.length(); i++){
			char current = in.charAt(i);
			if(current != '{' && current != ')' && current != ' ' && current != '}' 
					&& current != '(' ){
				return i;
			}
		}
		//if no more non space/nonbracket characters
		return -1;
	}

	//helper method to skip any elemets equal to whatever is passed in
	private void skipElementsEqualToX(Scanner sc, String x){
		String element = sc.next();
		while(element.equals(x)){
			element = sc.next();
		}
	}
	//checks that all objects have matching brackets
	private boolean bracketsOkay(String in){
		int numOpenBraces = 0;
		int numCloseBraces = 0;

		for(int i = 0; i < in.length(); i++){
			char currentChar = in.charAt(i);
			if(currentChar == '{'){
				openBraces++;
			}else if(currentChar == '}'){
				closeBraces++;
			}
		}
		return numOpenBraces == numCloseBraces;
	}
}
