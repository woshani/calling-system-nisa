Versi yang ini tidak perlu lagi mengubah database path, output folder path, dan FFMPEG path.

Sistem akan secara otomatis menemukan semua path tersebut.

Ketika build project, folder "FFMPEG", "WAVCollection", dan "OutputTemp"
akan secara otomatis dikopikan ke folder "dist",
sehingga pengguna tidak perlu repot lagi.

"WAVCollection" dan "FFMPEG" aku kosongkan dulu (hemat kuota).
Kalau mau pakai cukup kopikan semua WAV file di folder "WAVCollection",
dan kopi file "ffmpeg.exe", "ffplay.exe", dan "ffprobe.exe" ke folder "FFMPEG".
Semua WAV file dijadikan 1 folder tanpa sub-folder.