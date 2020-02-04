/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.coverageio.gdal.hdf5;

import java.util.logging.Level;
import java.util.logging.Logger;
import it.geosolutions.imageio.plugins.gdalhdf5.HDF5ImageReaderSpi;
import org.geotools.coverageio.gdal.BaseGDALGridFormat;
import org.geotools.data.DataSourceException;
import org.geotools.util.factory.Hints;
import org.opengis.coverage.grid.Format;
import org.opengis.geometry.MismatchedDimensionException;
                
/**
 *
 * @author Ulrich Loup
 */
public final class HDF5Format extends BaseGDALGridFormat implements Format{
    
/**
 * An implementation of {@link Format} for the HDF5 format.
 *
 * @author Ulrich Loup
 * @since 2.5.x
 */

    /** Logger. */
    private static final Logger LOGGER =
            org.geotools.util.logging.Logging.getLogger(HDF5Format.class);

    /** Creates an instance and sets the metadata. */
    public HDF5Format() {
        super(new HDF5ImageReaderSpi());

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("Creating a new HDF5Format.");
        }

        setInfo();
    }

    private static final InfoWrapper INFO = new InfoWrapper("HDF5 Coverage Format", "HDF5");

    /** Sets the metadata information. */
    protected void setInfo() {
        setInfo(INFO);
    }

    /** @see org.geotools.data.coverage.grid.AbstractGridFormat#getReader(Object, Hints) */
    public HDF5Reader getReader(Object source, Hints hints) {
        try {
            return new HDF5Reader(source, hints);
        } catch (MismatchedDimensionException e) {
            final RuntimeException re = new RuntimeException();
            re.initCause(e);
            throw re;
        } catch (DataSourceException e) {
            final RuntimeException re = new RuntimeException();
            re.initCause(e);
            throw re;
        }
    }
}
