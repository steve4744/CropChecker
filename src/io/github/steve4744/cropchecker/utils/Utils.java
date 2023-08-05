/*
 * MIT License

Copyright (c) 2023 steve4744

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package io.github.steve4744.cropchecker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	private final static Pattern HEXCOLOUR = Pattern.compile("<#([A-Fa-f0-9]){6}>");

	public static String translateColourCodes(String message) {
		Matcher matcher = HEXCOLOUR.matcher(message);
		while (matcher.find()) {
			StringBuilder sb = new StringBuilder();
			final ChatColor hexColour = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
			sb.append(message.substring(0, matcher.start())).append(hexColour).append(message.substring(matcher.end()));
			message = sb.toString();
			matcher = HEXCOLOUR.matcher(message);
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
