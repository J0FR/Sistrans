SELECT c.table_name AS NombreTabla, cc.column_name AS NombreColsPK, tc.data_type AS TipoDeDato 
FROM ALL_CONSTRAINTS c 
JOIN ALL_CONS_COLUMNS cc ON c.owner = cc.owner AND c.constraint_name = cc.constraint_name 
JOIN ALL_TAB_COLUMNS tc ON cc.owner = tc.owner AND cc.table_name = tc.table_name AND cc.column_name = tc.column_name 
WHERE c.owner = 'PARRANDEROS' 
    AND c.constraint_type = 'P' 
ORDER BY c.table_name ASC, cc.column_name ASC; 