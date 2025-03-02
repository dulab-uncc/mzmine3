/*
 * Copyright (c) 2004-2024 The MZmine Development Team
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.mzmine.modules.dataanalysis.rowsboxplot;

import io.github.mzmine.datamodel.AbundanceMeasure;
import io.github.mzmine.datamodel.features.FeatureListRow;
import io.github.mzmine.modules.visualization.projectmetadata.table.columns.MetadataColumn;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.Nullable;

public class RowsBoxplotModel {

  private final ObjectProperty<List<FeatureListRow>> selectedRows = new SimpleObjectProperty<>();
  private final ObjectProperty<@Nullable MetadataColumn<?>> groupingColumn = new SimpleObjectProperty<>();
  private final ObjectProperty<@Nullable RowBarDataset> dataset = new SimpleObjectProperty<>();
  private final ObjectProperty<AbundanceMeasure> abundanceMeasure = new SimpleObjectProperty<>();

  public List<FeatureListRow> getSelectedRows() {
    return selectedRows.get();
  }

  public ObjectProperty<List<FeatureListRow>> selectedRowsProperty() {
    return selectedRows;
  }

  public void setSelectedRows(List<FeatureListRow> selectedRows) {
    this.selectedRows.set(selectedRows);
  }

  public @Nullable MetadataColumn<?> getGroupingColumn() {
    return groupingColumn.get();
  }

  public ObjectProperty<@Nullable MetadataColumn<?>> groupingColumnProperty() {
    return groupingColumn;
  }

  public void setGroupingColumn(@Nullable MetadataColumn<?> groupingColumn) {
    this.groupingColumn.set(groupingColumn);
  }

  public @Nullable RowBarDataset getDataset() {
    return dataset.get();
  }

  public ObjectProperty<@Nullable RowBarDataset> datasetProperty() {
    return dataset;
  }

  public void setDataset(@Nullable RowBarDataset dataset) {
    this.dataset.set(dataset);
  }

  public AbundanceMeasure getAbundanceMeasure() {
    return abundanceMeasure.get();
  }

  public ObjectProperty<AbundanceMeasure> abundanceMeasureProperty() {
    return abundanceMeasure;
  }

  public void setAbundanceMeasure(AbundanceMeasure abundanceMeasure) {
    this.abundanceMeasure.set(abundanceMeasure);
  }
}
