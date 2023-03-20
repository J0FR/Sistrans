SELECT tc.table_name AS NombreTabla, tc.column_name AS NombreColumna, tc.data_type AS TipoDeDato, COALESCE (c.constraint_name, 'NO TIENE')  AS NombreRestriccion, NULLABLE AS PermiteNulos 
FROM ALL_TAB_COLUMNS tc 
LEFT JOIN ALL_CONS_COLUMNS cc ON tc.owner = cc.owner AND tc.table_name = cc.table_name AND tc.column_name = cc.column_name 
LEFT JOIN ALL_CONSTRAINTS c ON cc.owner = c.owner AND cc.constraint_name = c.constraint_name 
WHERE tc.owner = 'PARRANDEROS' 
ORDER BY tc.table_name ASC, tc.column_name ASC, NombreRestriccion ASC; 