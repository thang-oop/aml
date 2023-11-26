package vn.com.pvcombank.service;

import com.monitorjbl.xlsx.StreamingReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class BLUploadFileConvertService {

    public InputStream convert(InputStream stream, Map<String, String[]> map) {
        final List<String> desCols = new ArrayList<>(map.keySet()); //cols dich
        Collections.reverse(desCols);

        final Map<String, Integer> mapSrcCols = new HashMap<>(); //cols nguon -> index dich
        map.forEach(
            (k, vs) -> {
                for (String v : vs) mapSrcCols.put(v, desCols.indexOf(k));
            }
        );

        final Iterator<Row> sheet = StreamingReader.builder().open(stream).getSheetAt(0).iterator();

        final Map<Integer, Integer> mapIndex = new HashMap<>(); //indexRaw -> index dich
        for (Cell cell : sheet.next()) { //headers
            final Integer mapSrcIndex = mapSrcCols.get(cell.getStringCellValue());
            if (mapSrcIndex != null) mapIndex.put(cell.getColumnIndex(), mapSrcIndex);
        }

        final StringBuilder sb = new StringBuilder(String.join(",", desCols)); //write headers
        while (sheet.hasNext()) {
            final int n = desCols.size();
            final Temps[] values = new Temps[n];
            for (int i = 0; i < n; i++) values[i] = new Temps();

            for (Cell c : sheet.next()) {
                final int rawColIndex = c.getColumnIndex();
                if (mapIndex.containsKey(rawColIndex)) {
                    final String rawS = c.getStringCellValue().trim();
                    if (!rawS.isEmpty()) values[mapIndex.get(rawColIndex)].value.add(rawS);
                }
            }

            final String line = Arrays.stream(values).map(temps -> String.join(" ", temps.value)).collect(Collectors.joining(","));
            sb.append('\n').append(line);
        }

        return new ByteArrayInputStream(sb.toString().getBytes());
    }
}

class Temps {

    final List<String> value = new ArrayList<>();
}
