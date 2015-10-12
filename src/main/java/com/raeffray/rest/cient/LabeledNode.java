package com.raeffray.rest.cient;

import com.raeffray.raw.data.RawData;

/**
 * A node and its respective labels to be persisted on the DB.
 */
public class LabeledNode {

    private String[] labels;

    private RawData node;

    public LabeledNode(String[] labels, RawData node) {
        this.labels = labels;
        this.node = node;
    }

    public String[] getLabels() {
        return labels;
    }

    public RawData getNode() {
        return node;
    }
}
