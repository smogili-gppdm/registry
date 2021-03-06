/**
 * Copyright 2017 Hortonworks.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.hortonworks.registries.schemaregistry;

import com.hortonworks.registries.common.Schema;
import com.hortonworks.registries.storage.PrimaryKey;
import com.hortonworks.registries.storage.catalog.AbstractStorable;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class SchemaBranchStorable extends AbstractStorable {

    public static final String NAME_SPACE = "schema_branch";

    public static final String ID = "id";

    private Long id;
    private String name;
    private String description;
    private Long timestamp;


    @Override
    @JsonIgnore
    public String getNameSpace() {
        return NAME_SPACE;
    }

    @Override
    @JsonIgnore
    public PrimaryKey getPrimaryKey() {
        Map<Schema.Field, Object> values = new HashMap<>();
        values.put(new Schema.Field(SchemaFieldInfo.NAME, Schema.Type.STRING), name);
        return new PrimaryKey(values);
    }

    public SchemaBranchStorable() {

    }

    public SchemaBranchStorable(String name) {
        this(name, null, null);
    }

    public SchemaBranchStorable(String name, String description) {
        this(name, description,null);
    }

    public SchemaBranchStorable(String name, String description, Long timestamp) {
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public static SchemaBranchStorable from(SchemaBranch schemaBranch) {
        SchemaBranchStorable schemaBranchStorable = new SchemaBranchStorable();
        schemaBranchStorable.setId(schemaBranch.getId());
        schemaBranchStorable.setName(schemaBranch.getName());
        schemaBranchStorable.setDescription(schemaBranch.getDescription());
        schemaBranchStorable.setTimestamp(schemaBranch.getTimestamp() == null ? System.currentTimeMillis() : schemaBranch.getTimestamp());
        return schemaBranchStorable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SchemaBranchStorable {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchemaBranchStorable that = (SchemaBranchStorable) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    public SchemaBranch toSchemaBranch() {
        return new SchemaBranch(this.id, this.name, this.description, this.timestamp);
    }
}
