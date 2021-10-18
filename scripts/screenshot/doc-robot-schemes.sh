#
#  Copyright (c) 2005-2021 Radiance Kirill Grouchnikov. All Rights Reserved.
#
#  Redistribution and use in source and binary forms, with or without
#  modification, are permitted provided that the following conditions are met:
#
#   o Redistributions of source code must retain the above copyright notice,
#     this list of conditions and the following disclaimer.
#
#   o Redistributions in binary form must reproduce the above copyright notice,
#     this list of conditions and the following disclaimer in the documentation
#     and/or other materials provided with the distribution.
#
#   o Neither the name of the copyright holder nor the names of
#     its contributors may be used to endorse or promote products derived
#     from this software without specific prior written permission.
#
#  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
#  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
#  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
#  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
#  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
#  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
#  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
#  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
#  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
#  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
#  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

export JAVA_HOME=`/usr/libexec/java_home -v 9`
alias JAVA="java"

RADIANCE_VERSION=5.0-SNAPSHOT
KOTLIN_VERSION=1.5.30
KOTLIN_COROUTINES_VERSION=1.5.2
CLASSPATH=../../drop/$RADIANCE_VERSION/tools/radiance-screenshot-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/tools/radiance-tools-common-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/core/radiance-laf-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/core/radiance-laf-extras-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/core/radiance-common-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/core/radiance-animation-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/demo/radiance-laf-demo-$RADIANCE_VERSION.jar:../../drop/$RADIANCE_VERSION/core/radiance-laf-extras-$RADIANCE_VERSION.jar:../../build/libs-tools/jgoodies-forms-1.9.0.jar:../../build/libs-tools/jgoodies-common-1.8.1.jar:../../build/libs-tools/kotlin-stdlib-$KOTLIN_VERSION.jar:../../build/libs-tools/kotlin-stdlib-common-$KOTLIN_VERSION.jar:../../build/libs-tools/kotlinx-coroutines-core-jvm-$KOTLIN_COROUTINES_VERSION.jar:../../build/libs-tools/kotlinx-coroutines-swing-$KOTLIN_COROUTINES_VERSION.jar

JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.AquaScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.BarbyPinkScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.BottleGreenScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.BrownScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.CharcoalScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.CremeScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DarkVioletScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DesertSandScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.EbonyScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.JadeForestScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.LightAquaScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.LimeGreenScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.OliveScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.OrangeScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.PurpleScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.RaspberryScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.SepiaScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.SteelBlueScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.SunGlareScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.SunsetScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.TerracottaScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.UltramarineScheme ../../docs/images/

JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedSaturatedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedDesaturatedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedInvertedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedNegatedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedHueShiftedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedShadedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedTintedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedTonedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedShiftedScheme ../../docs/images/
JAVA -cp $CLASSPATH org.pushingpixels.radiance.tools.screenshot.RobotMain org.pushingpixels.radiance.tools.screenshot.laf.schemes.DerivedShiftedBackgroundScheme ../../docs/images/