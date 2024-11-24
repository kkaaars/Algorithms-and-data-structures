#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

void solvePuzzle(std::vector<int>& moves,
                 std::vector<std::vector<std::vector<int>>>& boardStates,
                 std::set<std::pair<int, int>>& openSet,
                 std::vector<std::pair<int, int>>& emptyTiles,
                 std::vector<int>& parentState,
                 std::vector<int>& movedTile,
                 std::vector<int>& heuristicEstimates,
                 std::vector<int>& pathCosts,
                 int& rows,
                 int& cols) {
    int currentState = 0;
    std::vector<std::vector<int>> currentBoard = boardStates[0];
    int emptyX = 0, emptyY = 0;

    while (!openSet.empty()) {
        if (heuristicEstimates[currentState] == 0) {
            while (currentState) {
                moves.push_back(movedTile[currentState]);
                currentState = parentState[currentState];
            }
            return;
        }
        openSet.erase(openSet.begin());

        emptyX = emptyTiles[currentState].first;
        emptyY = emptyTiles[currentState].second;

        // Move Left
        if (emptyX > 0 && boardStates[currentState][emptyX - 1][emptyY] != movedTile[currentState]) {
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX - 1][emptyY]);
            movedTile.push_back(boardStates[currentState][emptyX - 1][emptyY]);
            parentState.push_back(currentState);
            emptyTiles.push_back({emptyX - 1, emptyY});
            pathCosts.push_back(pathCosts[currentState] + 1);
            int heuristicChange = std::abs((movedTile.back() - 1) / cols - emptyX) < std::abs((movedTile.back() - 1) / cols - (emptyX - 1)) ? -1 : 1;
            heuristicEstimates.push_back(heuristicEstimates[currentState] + heuristicChange);
            openSet.emplace(heuristicEstimates.back() + pathCosts.back(), boardStates.size());
            boardStates.push_back(currentBoard);
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX - 1][emptyY]);
        }

        // Move Right
        if (emptyX < rows - 1 && boardStates[currentState][emptyX + 1][emptyY] != movedTile[currentState]) {
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX + 1][emptyY]);
            movedTile.push_back(boardStates[currentState][emptyX + 1][emptyY]);
            parentState.push_back(currentState);
            emptyTiles.push_back({emptyX + 1, emptyY});
            pathCosts.push_back(pathCosts[currentState] + 1);
            int heuristicChange = std::abs((movedTile.back() - 1) / cols - emptyX) < std::abs((movedTile.back() - 1) / cols - (emptyX + 1)) ? -1 : 1;
            heuristicEstimates.push_back(heuristicEstimates[currentState] + heuristicChange);
            openSet.emplace(heuristicEstimates.back() + pathCosts.back(), boardStates.size());
            boardStates.push_back(currentBoard);
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX + 1][emptyY]);
        }

        // Move Up
        if (emptyY > 0 && boardStates[currentState][emptyX][emptyY - 1] != movedTile[currentState]) {
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX][emptyY - 1]);
            movedTile.push_back(boardStates[currentState][emptyX][emptyY - 1]);
            parentState.push_back(currentState);
            emptyTiles.push_back({emptyX, emptyY - 1});
            pathCosts.push_back(pathCosts[currentState] + 1);
            int heuristicChange = std::abs((movedTile.back() - 1) % cols - emptyY) < std::abs((movedTile.back() - 1) % cols - (emptyY - 1)) ? -1 : 1;
            heuristicEstimates.push_back(heuristicEstimates[currentState] + heuristicChange);
            openSet.emplace(heuristicEstimates.back() + pathCosts.back(), boardStates.size());
            boardStates.push_back(currentBoard);
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX][emptyY - 1]);
        }

        // Move Down
        if (emptyY < cols - 1 && boardStates[currentState][emptyX][emptyY + 1] != movedTile[currentState]) {
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX][emptyY + 1]);
            movedTile.push_back(boardStates[currentState][emptyX][emptyY + 1]);
            parentState.push_back(currentState);
            emptyTiles.push_back({emptyX, emptyY + 1});
            pathCosts.push_back(pathCosts[currentState] + 1);
            int heuristicChange = std::abs((movedTile.back() - 1) % cols - emptyY) < std::abs((movedTile.back() - 1) % cols - (emptyY + 1)) ? -1 : 1;
            heuristicEstimates.push_back(heuristicEstimates[currentState] + heuristicChange);
            openSet.emplace(heuristicEstimates.back() + pathCosts.back(), boardStates.size());
            boardStates.push_back(currentBoard);
            std::swap(currentBoard[emptyX][emptyY], currentBoard[emptyX][emptyY + 1]);
        }

        currentState = openSet.begin()->second;
        currentBoard = boardStates[currentState];
    }
}

int main() {
    std::ios::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    int rows, cols;
    std::cin >> rows >> cols;

    std::vector<std::vector<std::vector<int>>> boardStates(1);
    std::set<std::pair<int, int>> openSet;
    std::vector<std::pair<int, int>> emptyTiles(1);
    std::vector<int> parentState(1, -1);
    std::vector<int> movedTile(1, -1);
    std::vector<int> heuristicEstimates(1, 0);
    std::vector<int> pathCosts(1, 0);
    std::vector<int> moves;

    std::vector<std::vector<int>> initialBoard(rows, std::vector<int>(cols));

    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            std::cin >> initialBoard[i][j];
            if (initialBoard[i][j] != 0) {
                heuristicEstimates[0] += std::abs((initialBoard[i][j] - 1) % cols - j) + std::abs((initialBoard[i][j] - 1) / cols - i);
            } else {
                emptyTiles[0] = {i, j};
            }
        }
    }

    openSet.insert({heuristicEstimates[0], 0});
    boardStates[0] = initialBoard;

    solvePuzzle(moves, boardStates, openSet, emptyTiles, parentState, movedTile, heuristicEstimates, pathCosts, rows, cols);

    std::cout << moves.size();
    for (auto it = moves.rbegin(); it != moves.rend(); ++it) {
        std::cout << "\n" << *it;
    }

    return 0;
}