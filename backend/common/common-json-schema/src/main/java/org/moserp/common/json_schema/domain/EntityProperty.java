/*******************************************************************************
 * Copyright 2013 Thomas Letsch (contact@thomas-letsch.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.moserp.common.json_schema.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EntityProperty {

    private String title;
    private String key;
    private String format;
    private Boolean readOnly;
    private String type;
    private transient boolean required;
    private String href;
    @JsonProperty(value = "x-schema-form")
    private AngularSchemaFormExtension schemaFormExtension;

    /**
     * For select etc. types
     */
    private List<NameValueMap> titleMap;

    @JsonProperty(value = "enum")
    private List<String> enumeration;

    /**
     * For array type
     */
    private BusinessEntity items;
}
