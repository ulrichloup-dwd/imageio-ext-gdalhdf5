/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.coverageio.gdal.hdf5;

import it.geosolutions.imageio.plugins.gdalhdf5.HDF5ImageReaderSpi;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.coverage.grid.io.GridFormatFactorySpi;
import org.geotools.coverageio.BaseGridFormatFactorySPI;
import org.opengis.coverage.grid.Format;

/**
 * Implementation of the {@link Format} service provider interface for HDF5 files.
 *
 * @author uloup
 */
public final class HDF5FormatFactory extends BaseGridFormatFactorySPI
        implements GridFormatFactorySpi {
    /** Logger. */
    private static final Logger LOGGER =
            org.geotools.util.logging.Logging.getLogger(HDF5FormatFactory.class);

    /**
     * Tells me if the coverage plugin to access hdf5 is available or not.
     *
     * @return <code>true</code> if the plugin is available, <code>false</code> otherwise.
     */
    public boolean isAvailable() {
        boolean available = true;

        // if these classes are here, then the runtime environment has
        // access to JAI and the JAI ImageI/O toolbox.
        try {
            Class.forName("it.geosolutions.imageio.plugins.gdalhdf5.HDF5ImageReaderSpi");
            available = new HDF5ImageReaderSpi().isAvailable();

            if (LOGGER.isLoggable(Level.FINE)) {
                if (available) {
                    LOGGER.fine("HDF5FormatFactory is available.");
                } else {
                    LOGGER.fine("HDF5FormatFactory is not available.");
                }
            }
        } catch (ClassNotFoundException cnf) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("HDF5FormatFactory is not available.");
            }

            available = false;
        }

        return available;
    }

    /**
     * Creating a {@link HDF5Format}
     *
     * @return A {@link HDF5Format}
     */
    public HDF5Format createFormat() {
        return new HDF5Format();
    }
}