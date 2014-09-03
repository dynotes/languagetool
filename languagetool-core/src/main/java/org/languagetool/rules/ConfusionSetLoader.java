/* LanguageTool, a natural language style checker 
 * Copyright (C) 2014 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads a confusion set from a plain text file (UTF-8). Expects a file
 * where there is one confusion set per line, words separated by commas.
 * @since 2.7
 */
public class ConfusionSetLoader {

  public Map<String,ConfusionProbabilityRule.ConfusionSet> loadConfusionSet(InputStream inputStream) throws IOException {
    Map<String,ConfusionProbabilityRule.ConfusionSet> map = new HashMap<>();
    try (
      InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader br = new BufferedReader(reader)
    ) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("#")) {
          continue;
        }
        String[] words = line.split(",\\s*");
        ConfusionProbabilityRule.ConfusionSet confusionSet = new ConfusionProbabilityRule.ConfusionSet(words);
        for (String word : words) {
          map.put(word, confusionSet);
        }
      }
    }
    return map;
  }

}