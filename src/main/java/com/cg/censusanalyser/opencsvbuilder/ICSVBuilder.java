package com.cg.censusanalyser.opencsvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader reader, Class class1) throws CSVBuilderException;
	public List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;
}
