#!/bin/bash

# Library Management System - Run Script
# This script compiles and runs the Library Management System

echo "========================================="
echo "  Library Management System"
echo "========================================="
echo ""

# Function to install JDK
install_jdk() {
    echo ""
    echo "🔧 Attempting to install JDK..."
    echo ""
    
    # Detect OS
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux - check for package manager
        if command -v apt &> /dev/null; then
            echo "Detected Debian/Ubuntu system"
            echo "Running: sudo apt update && sudo apt install -y openjdk-21-jdk"
            sudo apt update && sudo apt install -y openjdk-21-jdk
            return $?
        elif command -v dnf &> /dev/null; then
            echo "Detected Fedora/RHEL system"
            echo "Running: sudo dnf install -y java-21-openjdk-devel"
            sudo dnf install -y java-21-openjdk-devel
            return $?
        elif command -v yum &> /dev/null; then
            echo "Detected CentOS/RHEL system"
            echo "Running: sudo yum install -y java-21-openjdk-devel"
            sudo yum install -y java-21-openjdk-devel
            return $?
        else
            echo "❌ Could not detect package manager (apt/dnf/yum)"
            return 1
        fi
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        if command -v brew &> /dev/null; then
            echo "Detected macOS with Homebrew"
            echo "Running: brew install openjdk@21"
            brew install openjdk@21
            return $?
        else
            echo "❌ Homebrew not found. Please install from https://brew.sh/"
            return 1
        fi
    else
        echo "❌ Unsupported operating system: $OSTYPE"
        return 1
    fi
}

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java is not installed."
    echo "Please install Java JDK 8 or higher."
    exit 1
fi

# Check if javac is installed
if ! command -v javac &> /dev/null; then
    echo "⚠️  Warning: javac (Java compiler) is not found."
    echo "You have JRE but need JDK to compile."
    echo ""
    
    # Ask user if they want to install JDK
    read -p "Would you like to install JDK automatically? (y/n): " -n 1 -r
    echo ""
    
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        if install_jdk; then
            echo ""
            echo "✓ JDK installed successfully!"
            echo ""
            
            # Verify installation
            if command -v javac &> /dev/null; then
                echo "✓ javac is now available"
            else
                echo "⚠️  javac not found in PATH. You may need to restart your terminal."
                echo "Manual installation command for Ubuntu/Debian:"
                echo "  sudo apt install openjdk-21-jdk"
                exit 1
            fi
        else
            echo ""
            echo "❌ Failed to install JDK automatically."
            echo ""
            echo "Please install manually:"
            echo "  Ubuntu/Debian: sudo apt install openjdk-21-jdk"
            echo "  Fedora/RHEL:   sudo dnf install java-21-openjdk-devel"
            echo "  macOS:         brew install openjdk@21"
            exit 1
        fi
    else
        echo ""
        echo "Installation cancelled. Please install JDK manually:"
        echo "  Ubuntu/Debian: sudo apt install openjdk-21-jdk"
        echo "  Fedora/RHEL:   sudo dnf install java-21-openjdk-devel"
        echo "  macOS:         brew install openjdk@21"
        exit 1
    fi
fi

echo "✓ Java Runtime version:"
java -version 2>&1 | head -n 1

if command -v javac &> /dev/null; then
    echo "✓ Java Compiler version:"
    javac -version 2>&1
fi
echo ""

echo "📦 Compiling source files..."
if find src -name '*.java' | xargs javac; then
    echo "✓ Compilation successful!"
    echo ""
    echo "▶️  Running application..."
    echo "========================================="
    echo ""
    java -cp src Main
    echo ""
    echo "========================================="
    echo "✓ Application finished successfully!"
else
    echo "❌ Compilation failed. Please check the errors above."
    exit 1
fi

