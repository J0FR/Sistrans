SELECT tc.table_name AS NombreDeTabla, tc.data_type AS TipoDeDato, COUNT(tc.column_name) AS NumColsTipoDato, ROUND(AVG(tc.avg_col_len), 2) AS PromedioLongitudCol 
FROM ALL_TAB_COLUMNS tc 
WHERE tc.owner = 'PARRANDEROS' 
GROUP BY tc.table_name, tc.data_type 
ORDER BY tc.table_name ASC, tc.data_type ASC, num_columns ASC; 