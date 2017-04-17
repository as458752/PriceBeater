/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2017-02-15 17:18:02 UTC)
 * on 2017-04-17 at 06:48:42 UTC 
 * Modify at your own risk.
 */

package com.example.jing.myapplication.backend.myApi.model;

/**
 * Model definition for BigInteger.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the myApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class BigInteger extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer bit;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer lowestSetBit;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getBit() {
    return bit;
  }

  /**
   * @param bit bit or {@code null} for none
   */
  public BigInteger setBit(java.lang.Integer bit) {
    this.bit = bit;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getLowestSetBit() {
    return lowestSetBit;
  }

  /**
   * @param lowestSetBit lowestSetBit or {@code null} for none
   */
  public BigInteger setLowestSetBit(java.lang.Integer lowestSetBit) {
    this.lowestSetBit = lowestSetBit;
    return this;
  }

  @Override
  public BigInteger set(String fieldName, Object value) {
    return (BigInteger) super.set(fieldName, value);
  }

  @Override
  public BigInteger clone() {
    return (BigInteger) super.clone();
  }

}
