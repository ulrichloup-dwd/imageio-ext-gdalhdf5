/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.coverageio.gdal.hdf5;

import it.geosolutions.imageio.plugins.gdalhdf5.HDF5ImageReaderSpi;
import org.geotools.coverageio.gdal.BaseGDALGridCoverage2DReader;
import org.geotools.data.DataSourceException;
import org.geotools.util.factory.Hints;
import org.opengis.coverage.grid.GridCoverageReader;
import org.opengis.coverage.grid.Format;

/**
 * A reader for HDF5 data sources creating a {@link GridCoverage} from the data.
 * 
 * @author uloup
 */
public class HDF5Reader extends BaseGDALGridCoverage2DReader implements GridCoverageReader {
    private static final String worldFileExt = "";

    /**
     * Creates a new instance of a {@link HDF5Reader}. I assume nothing about file extension.
     *
     * @param input Source object for which we want to build an {@link HDF5Reader}.
     * @throws DataSourceException
     */
    public HDF5Reader(Object input) throws DataSourceException {
        this(input, null);
    }

    /**
     * Creates a new instance of a {@link HDF5Reader}. I assume nothing about file extension.
     *
     * @param input Source object for which we want to build an {@link HDF5Reader}.
     * @param hints Hints to be used by this reader throughout his life.
     * @throws DataSourceException
     */
    public HDF5Reader(Object input, Hints hints) throws DataSourceException {
        super(input, hints, worldFileExt, new HDF5ImageReaderSpi());
    }

    /** @see org.opengis.coverage.grid.GridCoverageReader#getFormat() */
    public Format getFormat() {
        return new HDF5Format();
    }
}
