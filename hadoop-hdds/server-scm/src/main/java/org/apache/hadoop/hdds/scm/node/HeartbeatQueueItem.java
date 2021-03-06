/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hdds.scm.node;

import com.google.common.annotations.VisibleForTesting;
import org.apache.hadoop.hdds.protocol.DatanodeDetails;
import org.apache.hadoop.hdds.protocol.proto
    .StorageContainerDatanodeProtocolProtos.NodeReportProto;

import static org.apache.hadoop.util.Time.monotonicNow;

/**
 * This class represents the item in SCM heartbeat queue.
 */
public class HeartbeatQueueItem {
  private DatanodeDetails datanodeDetails;
  private long recvTimestamp;
  private NodeReportProto nodeReport;

  /**
   *
   * @param datanodeDetails - datanode ID of the heartbeat.
   * @param recvTimestamp - heartbeat receive timestamp.
   * @param nodeReport - node report associated with the heartbeat if any.
   */
  HeartbeatQueueItem(DatanodeDetails datanodeDetails, long recvTimestamp,
      NodeReportProto nodeReport) {
    this.datanodeDetails = datanodeDetails;
    this.recvTimestamp = recvTimestamp;
    this.nodeReport = nodeReport;
  }

  /**
   * @return datanode ID.
   */
  public DatanodeDetails getDatanodeDetails() {
    return datanodeDetails;
  }

  /**
   * @return node report.
   */
  public NodeReportProto getNodeReport() {
    return nodeReport;
  }

  /**
   * @return heartbeat receive timestamp.
   */
  public long getRecvTimestamp() {
    return recvTimestamp;
  }

  /**
   * Builder for HeartbeatQueueItem.
   */
  public static class Builder {
    private DatanodeDetails datanodeDetails;
    private NodeReportProto nodeReport;
    private long recvTimestamp = monotonicNow();

    public Builder setDatanodeDetails(DatanodeDetails dnDetails) {
      this.datanodeDetails = dnDetails;
      return this;
    }

    public Builder setNodeReport(NodeReportProto report) {
      this.nodeReport = report;
      return this;
    }

    @VisibleForTesting
    public Builder setRecvTimestamp(long recvTime) {
      this.recvTimestamp = recvTime;
      return this;
    }

    public HeartbeatQueueItem build() {
      return new HeartbeatQueueItem(datanodeDetails, recvTimestamp, nodeReport);
    }
  }
}