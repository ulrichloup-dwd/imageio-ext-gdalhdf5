/*
 * Copyright (C) 2020 German Weather Service (DWD)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.geoserver.gdal;

import it.geosolutions.imageio.gdalframework.GDALUtilities;
import java.util.Optional;
import org.gdal.gdal.gdal;
import org.geoserver.platform.ModuleStatus;
import org.geotools.util.Version;
import org.geotools.util.factory.GeoTools;

/**
 *
 * @author Ulrich Loup
 */
public class HDF5Status implements ModuleStatus {

    @Override
    public String getModule() {
        return "gs-gdal";
    }

    @Override
    public Optional<String> getComponent() {
        return Optional.ofNullable("HDF5ImageReader");
    }

    @Override
    public String getName() {
        return "HDF5 Coverage Format";
    }

    @Override
    public Optional<String> getVersion() {
        Version v = GeoTools.getVersion(GDALUtilities.class);
        if (v == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(v.toString());
    }

    @Override
    public boolean isAvailable() {
        return GDALUtilities.isGDALAvailable();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Optional<String> getMessage() {
        String message = "JNI GDAL Wrapper Version: " + getGDALWrapperJarVersion().orElse("null");
        message += "\njava.library.path: " + System.getProperty("java.library.path", "");
        message += "\nGDAL_HOME: " + System.getenv("GDAL_HOME");
        if (isAvailable()) {
                    message += metaData();
        }
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<String> getDocumentation() {
        return Optional.ofNullable("");
    }

    public Optional<String> getGDALWrapperJarVersion() {
        if (isAvailable()) {
            Version v = GeoTools.getVersion(gdal.class);
            if (v == null) {
                return Optional.empty();
            }
            return Optional.ofNullable(v.toString());
        } else {
            return Optional.ofNullable("unavailable");
        }
    }

    String metaData() {
        StringBuffer msg = new StringBuffer();
        msg.append("\nGDAL Version: " + gdal.VersionInfo("RELEASE_NAME"));
        msg.append("\nGDAL Release Date: " + gdal.VersionInfo("RELEASE_DATE"));
        msg.append("\nGDAL Build Info: " + gdal.VersionInfo("BUILD_INFO"));
        return msg.toString();
    }
}
