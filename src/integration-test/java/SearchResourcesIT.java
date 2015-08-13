/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.SearchResult;
import com.smartsheet.api.models.Sheet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class SearchResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testSearch() throws IOException, SmartsheetException {
        SearchResult result = smartsheet.searchResources().search("aditi");
        assertNotNull(result.getResults());
    }

    @Test
    public void testSearchSheet() throws IOException, SmartsheetException {
        //create sheet
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        addRows(sheet.getId());

        SearchResult searchSheet = smartsheet.searchResources().searchSheet(sheet.getId(), "test");
        assertNotNull(searchSheet);

        //clean up
        deleteSheet(sheet.getId());
    }
}