extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global _java.util.Arrays_CONSTRUCTOR_Arrays#
_java.util.Arrays_CONSTRUCTOR_Arrays#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Arrays
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.util.Arrays_METHOD_equals#boolean$$#boolean$$#
_java.util.Arrays_METHOD_equals#boolean$$#boolean$$#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_2:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg a2
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_3:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne _java.util.Arrays_IfNEQ_4
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_1
_java.util.Arrays_IfNEQ_4:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_1:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_IfThenStmt_5
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_6:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.util.Arrays_IfThenStmt_5:
; *****END Ifstmt node *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_9:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.util.Arrays_ForLoop_7:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_11:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_12:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.util.Arrays_IfLT_13
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_10
_java.util.Arrays_IfLT_13:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_10:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_doneFor_8
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_16:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.util.Arrays_noError_17 
call __exception ;  nullPointerException
_java.util.Arrays_noError_17:
cmp ecx, ebx 
jge _java.util.Arrays_noError_18 
call __exception ; negative array index
_java.util.Arrays_noError_18:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.util.Arrays_noError_19 
call __exception ; ArrayIndexOutOfBoundsException
_java.util.Arrays_noError_19:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_15:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg a2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_21:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.util.Arrays_noError_22 
call __exception ;  nullPointerException
_java.util.Arrays_noError_22:
cmp ecx, ebx 
jge _java.util.Arrays_noError_23 
call __exception ; negative array index
_java.util.Arrays_noError_23:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.util.Arrays_noError_24 
call __exception ; ArrayIndexOutOfBoundsException
_java.util.Arrays_noError_24:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_20:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne _java.util.Arrays_IfNEQ_25
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_14
_java.util.Arrays_IfNEQ_25:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_14:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_IfThenStmt_26
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_27:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.util.Arrays_IfThenStmt_26:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_29:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_30:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.util.Arrays_beginNonStringAdd_31:
add eax, ebx ; +
_java.util.Arrays_endNonStringAdd_32:
_java.util.Arrays_BinOpEndLabel_28:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.util.Arrays_ForLoop_7
_java.util.Arrays_doneFor_8:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_33:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.util.Arrays_METHOD_equals#char$$#char$$#
_java.util.Arrays_METHOD_equals#char$$#char$$#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_35:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg a2
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_36:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne _java.util.Arrays_IfNEQ_37
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_34
_java.util.Arrays_IfNEQ_37:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_34:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_IfThenStmt_38
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_39:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.util.Arrays_IfThenStmt_38:
; *****END Ifstmt node *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_42:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.util.Arrays_ForLoop_40:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_44:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_45:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.util.Arrays_IfLT_46
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_43
_java.util.Arrays_IfLT_46:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_43:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_doneFor_41
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg a1
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_49:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.util.Arrays_noError_50 
call __exception ;  nullPointerException
_java.util.Arrays_noError_50:
cmp ecx, ebx 
jge _java.util.Arrays_noError_51 
call __exception ; negative array index
_java.util.Arrays_noError_51:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.util.Arrays_noError_52 
call __exception ; ArrayIndexOutOfBoundsException
_java.util.Arrays_noError_52:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_48:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg a2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_54:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.util.Arrays_noError_55 
call __exception ;  nullPointerException
_java.util.Arrays_noError_55:
cmp ecx, ebx 
jge _java.util.Arrays_noError_56 
call __exception ; negative array index
_java.util.Arrays_noError_56:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.util.Arrays_noError_57 
call __exception ; ArrayIndexOutOfBoundsException
_java.util.Arrays_noError_57:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_53:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne _java.util.Arrays_IfNEQ_58
mov eax, 0
jmp _java.util.Arrays_BinOpEndLabel_47
_java.util.Arrays_IfNEQ_58:
mov eax, 1
_java.util.Arrays_BinOpEndLabel_47:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.util.Arrays_IfThenStmt_59
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_60:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.util.Arrays_IfThenStmt_59:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_62:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_63:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.util.Arrays_beginNonStringAdd_64:
add eax, ebx ; +
_java.util.Arrays_endNonStringAdd_65:
_java.util.Arrays_BinOpEndLabel_61:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.util.Arrays_ForLoop_40
_java.util.Arrays_doneFor_41:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.util.Arrays_BinOpEndLabel_66:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
