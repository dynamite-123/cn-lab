#!/bin/bash

# Configuration
PORT=8000

# 1. Kill any process currently running on this port
# (Redirects output to null to keep it quiet)
fuser -k $PORT/tcp > /dev/null 2>&1

# 2. Get the Local IP Address (Arch/Linux compatible method)
MY_IP=$(ip route get 1 | awk '{print $7; exit}')

# 3. Display Info
echo "========================================================"
echo "🚀 File Server Started"
echo "📂 Serving: $(pwd)"
echo "🔗 URL:     http://$MY_IP:$PORT"
echo "========================================================"
echo "Client Command (Single File):"
echo "wget http://$MY_IP:$PORT/filename.ext"
echo ""
echo "Client Command (Whole Folder):"
# Note: The backslashes below are critical for the quotes to work
echo "wget -r -np -nH --cut-dirs=1 -R \"index.html*\" http://$MY_IP:$PORT/"
echo "========================================================"
echo "Press Ctrl+C to stop"
echo ""

# 4. Start the Server
python3 -m http.server $PORT