/*
 *  Copyright 2018 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.cloud.gcp.data.datastore.it;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.google.cloud.datastore.Key;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.data.annotation.Id;

/**
 * @author Dmitry Solomakha
 */
public class AncestorEntity {
	@Id
	Long id;

	String name;

	@Descendants
	List<DescendatEntry> descndants;

	AncestorEntity(String name, List<DescendatEntry> descndants) {
		this.name = name;
		this.descndants = descndants;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AncestorEntity that = (AncestorEntity) o;
		return Objects.equals(this.name, that.name) &&
				new HashSet<>(this.descndants).equals(new HashSet<>(that.descndants));
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.name, this.descndants);
	}

	public static class DescendatEntry {
		@Id
		Key id;

		String name;

		DescendatEntry(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			DescendatEntry that = (DescendatEntry) o;
			return Objects.equals(this.name, that.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.name);
		}
	}
}
