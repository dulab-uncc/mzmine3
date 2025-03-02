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

package io.github.mzmine.modules.dataanalysis.significance.anova;

import io.github.mzmine.datamodel.MZmineProject;
import io.github.mzmine.datamodel.features.FeatureList;
import io.github.mzmine.modules.MZmineModuleCategory;
import io.github.mzmine.modules.MZmineProcessingModule;
import io.github.mzmine.modules.dataanalysis.significance.RowSignificanceTest;
import io.github.mzmine.modules.dataanalysis.significance.RowSignificanceTestModule;
import io.github.mzmine.modules.visualization.projectmetadata.MetadataColumnDoesNotExistException;
import io.github.mzmine.parameters.ParameterSet;
import io.github.mzmine.parameters.ValuePropertyComponent;
import io.github.mzmine.parameters.parametertypes.metadata.MetadataGroupingComponent;
import io.github.mzmine.taskcontrol.Task;
import io.github.mzmine.util.ExitCode;
import java.time.Instant;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;

public class AnovaModule implements MZmineProcessingModule, RowSignificanceTestModule<String> {

  private static final Logger logger = Logger.getLogger(AnovaModule.class.getName());

  private static final String MODULE_NAME = "One-way ANOVA Test";
  private static final String MODULE_DESCRIPTION = "Calculates one-way ANOVA test on the intensities of aligned features.";

  @Override
  public @NotNull String getName() {
    return MODULE_NAME;
  }

  @Override
  public @NotNull String getDescription() {
    return MODULE_DESCRIPTION;
  }

  @Override
  @NotNull
  public ExitCode runModule(@NotNull MZmineProject project, @NotNull ParameterSet parameters,
      @NotNull Collection<Task> tasks, @NotNull Instant moduleCallDate) {

    FeatureList[] featureLists = parameters.getValue(AnovaParameters.featureLists)
        .getMatchingFeatureLists();

    for (FeatureList featureList : featureLists) {
      tasks.add(new AnovaTask(featureList, parameters, moduleCallDate));
    }

    return ExitCode.OK;

  }

  @Override
  public @NotNull MZmineModuleCategory getModuleCategory() {
    return MZmineModuleCategory.DATAANALYSIS;
  }

  @Override
  public @NotNull Class<? extends ParameterSet> getParameterSetClass() {
    return AnovaParameters.class;
  }

  @Override
  public RowSignificanceTest getInstance(ValuePropertyComponent<String> component) {
    try {
      return new AnovaTest(component.valueProperty().getValue());
    } catch (MetadataColumnDoesNotExistException e) {
      logger.log(Level.WARNING, e.getMessage(), e);
      return null;
    }
  }

  @Override
  public @NotNull <C extends Region & ValuePropertyComponent<String>> C createConfigurationComponent() {
    return (C) new MetadataGroupingComponent();
  }

}
