/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.test.docs;

import ratpack.func.Block;
import ratpack.test.docs.executer.ExtractedSnippet;

public class SnippetFixture {

  public void around(Block action) throws Exception {
    action.execute();
  }

  public String transform(String text) {
    return text;
  }

  public String pre() {
    return "";
  }

  public String post() {
    return "";
  }

  public Integer getOffset() {
    return pre().split("\n").length;
  }

  public String build(ExtractedSnippet extractedSnippet) {
    return
      extractedSnippet.getPackageAndImports()
      + pre()
      + transform(extractedSnippet.getBody())
      + post();
  }
}
