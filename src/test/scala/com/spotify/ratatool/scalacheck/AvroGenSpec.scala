/*
 * Copyright 2016 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.ratatool.scalacheck

import com.google.cloud.dataflow.sdk.coders.AvroCoder
import com.google.cloud.dataflow.sdk.util.CoderUtils
import com.spotify.ratatool.avro.specific.TestRecord
import org.scalacheck.Prop.forAll
import org.scalacheck._

object AvroGenSpec extends Properties("AvroGen") {

  val coder = AvroCoder.of(classOf[TestRecord])

  property("round trips") = forAll (AvroGen.avroOf[TestRecord]) { m =>
    val bytes = CoderUtils.encodeToByteArray(coder, m)
    m == CoderUtils.decodeFromByteArray(coder, bytes)
  }

}
