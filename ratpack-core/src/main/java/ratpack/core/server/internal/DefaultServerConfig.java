/*
 * Copyright 2014 the original author or authors.
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

package ratpack.core.server.internal;

import com.google.common.collect.ImmutableSet;
import io.netty.handler.ssl.SslContext;
import io.netty.util.Mapping;
import ratpack.config.ConfigData;
import ratpack.config.ConfigObject;
import ratpack.config.internal.DelegatingConfigData;
import ratpack.config.FileSystemBinding;
import ratpack.core.server.DecodingErrorLevel;
import ratpack.core.server.NoBaseDirException;
import ratpack.core.server.ServerConfig;
import ratpack.func.Nullable;

import java.net.InetAddress;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

public class DefaultServerConfig extends DelegatingConfigData implements ServerConfig {

  private final ServerConfigData serverConfigData;
  private final Optional<FileSystemBinding> baseDir;
  private final ImmutableSet<ConfigObject<?>> requiredConfig;

  public DefaultServerConfig(ConfigData configData, ImmutableSet<ConfigObject<?>> requiredConfig) {
    super(configData);
    this.requiredConfig = requiredConfig;
    this.serverConfigData = get("/server", ServerConfigData.class);
    this.baseDir = Optional.ofNullable(serverConfigData.getBaseDir());
  }

  @Override
  public int getPort() {
    return serverConfigData.getPort();
  }

  @Nullable
  @Override
  public InetAddress getAddress() {
    return serverConfigData.getAddress();
  }

  @Override
  public ImmutableSet<ConfigObject<?>> getRequiredConfig() {
    return requiredConfig;
  }

  @Override
  public boolean isDevelopment() {
    return serverConfigData.isDevelopment();
  }

  @Override
  public int getThreads() {
    return serverConfigData.getThreads();
  }

  @Override
  public boolean isRegisterShutdownHook() {
    return serverConfigData.isRegisterShutdownHook();
  }

  @Override
  public URI getPublicAddress() {
    return serverConfigData.getPublicAddress();
  }

  @Override
  public Mapping<String, SslContext> getSslContext() {
    return serverConfigData.getSslContext();
  }

  @Override
  public int getMaxContentLength() {
    return serverConfigData.getMaxContentLength();
  }

  @Override
  public boolean isHasBaseDir() {
    return serverConfigData.getBaseDir() != null;
  }

  @Override
  public int getMaxChunkSize() {
    return serverConfigData.getMaxChunkSize();
  }

  @Override
  public int getMaxInitialLineLength() {
    return serverConfigData.getMaxInitialLineLength();
  }

  @Override
  public int getMaxHeaderSize() {
    return serverConfigData.getMaxHeaderSize();
  }

  @Override
  public Duration getIdleTimeout() {
    return serverConfigData.getIdleTimeout();
  }

  @Override
  public FileSystemBinding getBaseDir() throws NoBaseDirException {
    return baseDir.orElseThrow(() -> new NoBaseDirException("No base dir has been set"));
  }

  @Override
  public Optional<Integer> getConnectTimeoutMillis() {
    return serverConfigData.getConnectTimeoutMillis();
  }

  @Override
  public Optional<Integer> getMaxMessagesPerRead() {
    return serverConfigData.getMaxMessagesPerRead();
  }

  @Override
  public Optional<Integer> getReceiveBufferSize() {
    return serverConfigData.getReceiveBufferSize();
  }

  @Override
  public Optional<Integer> getConnectQueueSize() {
    return serverConfigData.getConnectQueueSize();
  }

  @Override
  public Optional<Integer> getWriteSpinCount() {
    return serverConfigData.getWriteSpinCount();
  }

  @Override
  public Optional<Path> getPortFile() {
    return serverConfigData.getPortFile();
  }

  @Override
  public DecodingErrorLevel getDecodingErrorLevel() {
    return serverConfigData.getDecodingErrorLevel();
  }
}
