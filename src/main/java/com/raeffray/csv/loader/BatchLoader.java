package com.raeffray.csv.loader;

import com.raeffray.csv.InstanceHandler;
import com.raeffray.raw.data.RawData;
import com.raeffray.rest.cient.LabeledNode;
import com.raeffray.rest.cient.RestClient;
import org.apache.commons.lang3.Validate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mau on 12/10/15.
 */
public class BatchLoader implements InstanceHandler<RawData> {

    private int batchSize = 10;

    private List<LabeledNode> batchBuffer;

    public BatchLoader(int batchSize) {
        Validate.isTrue(batchSize > 0, "batchSize=%d (should be greater than 0)", batchSize);
        this.batchSize = batchSize;
        this.batchBuffer = new LinkedList<>();
    }

    @Override
    public void processInstance(RawData instance) {
        batchBuffer.add(new LabeledNode(labelsFor(instance), instance));
        if (batchBuffer.size() >= batchSize) {
            flush();
        }
    }

    public void flush() {
        if (batchBuffer.size() > 0) {
            RestClient.getInstance().createLabeledNodes(batchBuffer);
            batchBuffer.clear();
        }
    }
    private String[] labelsFor(RawData instance) {
        return new String[]{instance.getClass().getSimpleName()};
    }
}
