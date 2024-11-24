#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <map>
#include <iterator>

std::vector<std::string> getStrings() {
    std::ifstream file("input.txt");
    std::string line;
    std::vector<std::string> lines;
    while (std::getline(file, line)) {
        lines.push_back(line);
    }
    return lines;
}

int main() {
    std::vector<std::string> lines = getStrings();
    int n = std::stoi(lines[0]);
    std::map<int, int> rightDesk;
    std::vector<int> leftDesk(n);
    std::istringstream iss(lines[1]);
    std::vector<std::string> tokens{ std::istream_iterator<std::string>{iss}, std::istream_iterator<std::string>{} };
    for (int i = 0; i < n * n; ++i) {
        rightDesk[std::stoi(tokens[i])]++;
    }
    auto it = rightDesk.begin();

    leftDesk[0] = (it->first >> 1);
    it->second--;

    for (int j = 1; j < n; j++) {
        while (it->second == 0) {
            ++it;
        }
        leftDesk[j] = (it->first - leftDesk[0]);
        if (j == n - 1) {
            break;
        }
        for (int i = 0; i < j; i++) {
            rightDesk[leftDesk[i] + leftDesk[j]] -= 2;
        }
        rightDesk[leftDesk[j] << 1]--;
    }

    std::ofstream writer("output.txt");
    for (int a : leftDesk) {
        writer << a << "\n";
    }
    writer.close();

    return 0;
}